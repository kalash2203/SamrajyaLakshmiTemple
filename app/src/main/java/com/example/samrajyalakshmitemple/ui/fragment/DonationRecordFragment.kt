package com.example.samrajyalakshmitemple.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentDonationRecordBinding
import com.example.samrajyalakshmitemple.databinding.FragmentUserPanelBinding
import com.example.samrajyalakshmitemple.ui.adapter.DonationRecordAdapter
import com.example.samrajyalakshmitemple.ui.adapter.UserPanelAdapter
import com.example.samrajyalakshmitemple.viewModelFactory.DonationRecordViewModelFactory
import com.example.samrajyalakshmitemple.viewModelFactory.ShowUserPanelRecordViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.DonationRecordViewModel


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
    }
}