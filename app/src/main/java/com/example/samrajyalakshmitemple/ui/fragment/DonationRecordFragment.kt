package com.example.samrajyalakshmitemple.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentDonationRecordBinding
import com.example.samrajyalakshmitemple.ui.adapter.DonationRecordAdapter
import com.example.samrajyalakshmitemple.viewModelFactory.DonationRecordViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.DonationRecordViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class DonationRecordFragment : BaseFragment<FragmentDonationRecordBinding>() {
    private val donationRecordViewModel by viewModels<DonationRecordViewModel> {
        DonationRecordViewModelFactory(repository)
    }
    val TAG="DonationRecordFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDonationRecordBinding
            = FragmentDonationRecordBinding::inflate


    override fun setup() {
        donationRecordViewModel.donationRecordResponse()
        donationRecordViewModel.donationLiveData.observe(viewLifecycleOwner, Observer {
            val donationRecordAdapter= DonationRecordAdapter(requireContext(),it.data)
            val linearLayoutManager= LinearLayoutManager(requireContext())
            binding?.rvUserPanel?.layoutManager=linearLayoutManager
            binding?.rvUserPanel?.adapter=donationRecordAdapter
        })


        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_donation_record_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
    }
}