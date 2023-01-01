package com.example.samrajyalakshmitemple.ui.fragment

import ShowMyProfileViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentProfileBinding
import com.example.samrajyalakshmitemple.ui.ApplicationClass
import com.example.samrajyalakshmitemple.utils.GlideLoader
import com.example.samrajyalakshmitemple.viewModelFactory.ShowMyProfileViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment :BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding =
        FragmentProfileBinding::inflate

    private val userDetailsViewModel by viewModels<ShowMyProfileViewModel> {
        ShowMyProfileViewModelFactory(repository)
    }


    override fun setup() {
        userDetailsViewModel.apply {
            showMyProfileResponse(savedPrefManager.getEmail())

            showMyProfileLiveData.observe(viewLifecycleOwner, Observer {
                savedPrefManager.putUserDetails(
                    it.data?.name,
                    it.data?.email,
                    it.data?.phone,
                    it.data?.city,
                    it.data?.country,
                    it.data?.role,
                    it.data?.img
                )
                savedPrefManager.putToken(it.token)
                ApplicationClass().setToken(savedPrefManager.getToken()!!)
                binding?.txtCityField?.text = it.data?.city
                binding?.txtNameField?.text = it.data?.name
                binding?.txtEmailField?.text = it.data?.email
                binding?.txtNumberField?.text = it.data?.phone
                binding?.txtStateField?.text = it.data?.state
                binding?.txtCountryField?.text = it.data?.country
                GlideLoader(requireContext()).loadUserPicture(it.data?.img, binding?.imageView)

            })

        }
        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_pooja_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
        binding?.updateProfile?.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_my_profile_to_bottomSheetUpdateProfileDetailsFragment)
        }

    }
}