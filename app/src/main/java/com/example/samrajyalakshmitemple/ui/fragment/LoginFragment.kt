package com.example.samrajyalakshmitemple.ui.fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    val TAG="LoginFragment"
    private lateinit var auth: FirebaseAuth
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
            = FragmentLoginBinding::inflate


    override fun setup() {
        auth=FirebaseAuth.getInstance()
        binding?.txtNewAccount?.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_login_to_registerFragment)
        }
        binding?.txtForgotPswrd?.setOnClickListener {
            if(validation2())
            {
                forgotPassword()
            }
        }
        binding?.btnLogin?.setOnClickListener {

            if(validation())
            {
                login(
                    binding?.edtEmail?.text.toString().trim(),
                    binding?.edtPassword?.text.toString().trim()
                )
            }
        }

    }
    fun login(email:String,password:String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "loginWithEmail:success")
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    savedPrefManager.putLogin(true)
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_login_to_profileFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "loginWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun validation(): Boolean {
        if (binding?.edtEmail?.text.toString().trim() { it <= ' ' }.isEmpty()) {
            Toast.makeText(requireContext(), "Email Field is Empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding?.edtPassword?.text.toString().trim() { it <= ' ' }.isEmpty()) {
            Toast.makeText(requireContext(), "Password Field is Empty", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
    //validation of email and password editfields
    fun validation2() : Boolean{
        if (binding?.edtEmail?.text?.toString()?.trim(){it <= ' '}?.isEmpty()==true){
            Toast.makeText(requireContext(), "Email Field is Empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    //function of sending email to registered email address for resetting the password
    fun forgotPassword(){
        auth.sendPasswordResetEmail(binding?.edtEmail?.text?.trim().toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Show the toast message and finish the forgot password activity to go back to the login screen.
                    Toast.makeText(
                        requireContext(),
                        "Email sent successfully",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
    }
}