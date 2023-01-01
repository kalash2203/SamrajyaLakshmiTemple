package com.example.samrajyalakshmitemple.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentDonationForStoneBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DonationForTempleStone : BaseFragment<FragmentDonationForStoneBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDonationForStoneBinding =
        FragmentDonationForStoneBinding::inflate

    override fun setup() {

        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_donationForTempleStone_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
    }

    }