package com.example.samrajyalakshmitemple.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samrajyalakshmitemple.databinding.FragmentMyDonationsBinding
import com.example.samrajyalakshmitemple.ui.adapter.MyDonationAdapter
import com.example.samrajyalakshmitemple.viewModelFactory.MyDonationRecordViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.MyDonationRecordViewModel

class MyDonationsFragment : BaseFragment<FragmentMyDonationsBinding>() {
    private val myDonationsViewModel by viewModels<MyDonationRecordViewModel> {
        MyDonationRecordViewModelFactory(repository)
    }
    val TAG="MyDonationsFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyDonationsBinding
            = FragmentMyDonationsBinding::inflate


    override fun setup() {
        myDonationsViewModel.myDonationRecordResponse(savedPrefManager.getEmail()!!)
        myDonationsViewModel.myDonationRecordLiveData.observe(viewLifecycleOwner, Observer {
            val myDonationAdapter= MyDonationAdapter(requireContext(),it.data)
            val linearLayoutManager= LinearLayoutManager(requireContext())
            binding?.rvUserPanel?.layoutManager=linearLayoutManager
            binding?.rvUserPanel?.adapter=myDonationAdapter
        })
    }
}