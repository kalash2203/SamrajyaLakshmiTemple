package com.example.samrajyalakshmitemple.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.samrajyalakshmitemple.databinding.FragmentPoojaBinding
import com.example.samrajyalakshmitemple.ui.adapter.GetStartedSliderAdapter
import com.example.samrajyalakshmitemple.viewmodels.PoojaSevaViewModel
import java.util.*


class PoojaFragment : BaseFragment<FragmentPoojaBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPoojaBinding =
        FragmentPoojaBinding::inflate


    private val poojaSevaViewModel by viewModels<PoojaSevaViewModel>()
    override fun setup() {

        poojaSevaViewModel.setViewPagerList()


        poojaSevaViewModel.viewPagerLive1.observe(viewLifecycleOwner) {
            val itemsPagerAdapter =
                poojaSevaViewModel.viewPagerLive1.value?.let { it1 ->
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

