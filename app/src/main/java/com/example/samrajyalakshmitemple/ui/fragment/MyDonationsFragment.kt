package com.example.samrajyalakshmitemple.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentMyDonationsBinding
import com.example.samrajyalakshmitemple.ui.adapter.MyDonationAdapter
import com.example.samrajyalakshmitemple.viewModelFactory.MyDonationRecordViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.MyDonationRecordViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

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

        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_my_donation_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
    }
}