package com.example.samrajyalakshmitemple.ui.fragment
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentRegisterBinding
import com.example.samrajyalakshmitemple.ui.ApplicationClass
import com.example.samrajyalakshmitemple.viewModelFactory.RegisterViewModelFactory
import com.example.samrajyalakshmitemple.viewModelFactory.ShowMyProfileViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

 class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
     private val registerViewModel by viewModels<RegisterViewModel> {
         RegisterViewModelFactory(repository)
     }
    val TAG="RegisterFragment"
    private lateinit var auth: FirebaseAuth
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
            = FragmentRegisterBinding::inflate


    override fun setup() {
        auth=FirebaseAuth.getInstance()
        binding?.txtNewAccount?.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_registerFragment_to_login)
        }

            binding?.btnRegister?.setOnClickListener {

              if(validation())
              {
                  register(
                      binding?.edtEmail?.text.toString().trim(),
                      binding?.edtPassword?.text.toString().trim()
                  )
              }
            }

    }
    fun register(email:String,password:String) {
        auth.createUserWithEmailAndPassword(email,password)
            //return type of addOnCompleteListener is task.
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()
             registerUser(email)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
     fun registerUser(email: String)
     {
         registerViewModel.showMyProfileResponse(email) // register user on server
         registerViewModel.showMyProfileLiveData.observe(viewLifecycleOwner, Observer {
             if(it.message=="user created success")
             {
                 savedPrefManager.putToken(it.token)
                 ApplicationClass().setToken(savedPrefManager.getToken()!!)
                 registerViewModel.signUpUser(email,binding?.edtName?.text?.trim().toString())
             }
         })
         registerViewModel.signUpLiveData.observe(viewLifecycleOwner, Observer {
             if(it.message=="user updated success"){
                 savedPrefManager.putEmail(email)

                 Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                     .navigate(R.id.action_registerFragment_to_profileFragment)
             }
         })



     }
    fun validation(): Boolean {

        if (binding?.edtName?.text.toString().trim() { it <= ' ' }.isEmpty()) {
            Toast.makeText(requireContext(), "First Name Field is Empty", Toast.LENGTH_SHORT).show()
            return false
        }

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
}