package com.example.samrajyalakshmitemple.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentDonationsBinding
import com.example.samrajyalakshmitemple.ui.adapter.GetStartedSliderAdapter
import com.example.samrajyalakshmitemple.viewmodels.DonationsViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class DonationsFragment : BaseFragment<FragmentDonationsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDonationsBinding =
        FragmentDonationsBinding::inflate


    private val donationsViewModel by viewModels<DonationsViewModel>()
    override fun setup() {

        donationsViewModel.setViewPagerList()


        donationsViewModel.viewPagerLive1.observe(viewLifecycleOwner) {
            val itemsPagerAdapter =
                donationsViewModel.viewPagerLive1.value?.let { it1 ->
                    GetStartedSliderAdapter(
                        requireActivity(),
                        it1
                    )
                }


            binding?.viewPgrFragmentSplashIntroSlider?.apply {
                adapter = itemsPagerAdapter

                enableAutoScroll2(3)
            }
        }

        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_donations_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    fun ViewPager2.enableAutoScroll2(totalPages: Int): Timer {
        val autoTimerTask = Timer()
        var currentPageIndex = currentItem
        autoTimerTask.schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    currentItem = currentPageIndex++
                    if (currentPageIndex == totalPages) currentPageIndex = 0
                }
            }
        }, 2000, 2000)

        // Stop auto paging when user touch the view
        getRecyclerView().setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) autoTimerTask.cancel()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> autoTimerTask.cancel()
                MotionEvent.ACTION_UP -> enableAutoScroll2(3)
                MotionEvent.ACTION_OUTSIDE -> enableAutoScroll2(3)
            }
            false
        }



        return autoTimerTask // Return the reference for cancel
    }
    fun ViewPager2.getRecyclerView(): RecyclerView {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        return recyclerViewField.get(this) as RecyclerView
    }
}

