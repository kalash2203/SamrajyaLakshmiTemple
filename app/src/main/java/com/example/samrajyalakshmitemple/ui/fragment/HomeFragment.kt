package com.example.samrajyalakshmitemple.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.samrajyalakshmitemple.databinding.FragmentHomeBinding
import com.example.samrajyalakshmitemple.ui.adapter.GetStartedSliderAdapter
import com.example.samrajyalakshmitemple.viewmodels.HomeViewModel
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    private val homeViewModel by viewModels<HomeViewModel>()
    override fun setup() {

        homeViewModel.setViewPagerList()


        homeViewModel.viewPagerLive1.observe(viewLifecycleOwner) {
            val itemsPagerAdapter =
                homeViewModel.viewPagerLive1.value?.let { it1 ->
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
        homeViewModel.viewPagerLive2.observe(viewLifecycleOwner) {
            val itemsPagerAdapter =
                homeViewModel.viewPagerLive2.value?.let { it1 ->
                    GetStartedSliderAdapter(
                        requireActivity(),
                        it1
                    )
                }


            binding?.viewPgrFragmentSplashIntroSlider2?.apply {
                adapter = itemsPagerAdapter

                enableAutoScroll3(6)
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

    @SuppressLint("ClickableViewAccessibility")
    fun ViewPager2.enableAutoScroll3(totalPages: Int): Timer {
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
                MotionEvent.ACTION_UP -> enableAutoScroll3(6)
                MotionEvent.ACTION_OUTSIDE -> enableAutoScroll3(6)
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

