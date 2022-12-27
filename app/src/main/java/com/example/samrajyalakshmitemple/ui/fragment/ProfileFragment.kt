package com.example.samrajyalakshmitemple.ui.fragment

import ShowMyProfileViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.samrajyalakshmitemple.databinding.FragmentProfileBinding
import com.example.samrajyalakshmitemple.utils.GlideLoader
import com.example.samrajyalakshmitemple.viewModelFactory.ShowMyProfileViewModelFactory


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
                binding?.txtCityField?.text = it.data?.city
                binding?.txtNameField?.text = it.data?.name
                binding?.txtEmailField?.text = it.data?.email
                binding?.txtNumberField?.text = it.data?.phone
                binding?.txtStateField?.text = it.data?.state
                binding?.txtCountryField?.text = it.data?.country
                GlideLoader(requireContext()).loadUserPicture(it.data?.img, binding?.imageView)

            })

        }

    }
}