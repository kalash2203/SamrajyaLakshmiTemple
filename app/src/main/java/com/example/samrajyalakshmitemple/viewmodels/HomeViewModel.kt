package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.models.GetStartedSlider


class HomeViewModel(): ViewModel() {

    val viewPagerLive1 = MutableLiveData<List<GetStartedSlider>>()
    val viewPagerLive2 = MutableLiveData<List<GetStartedSlider>>()

    fun setViewPagerList() {


        val listItems1 = ArrayList<GetStartedSlider>()
        val listItems2 = ArrayList<GetStartedSlider>()



        listItems1.add(GetStartedSlider(R.drawable.logo_one))
        listItems1.add(GetStartedSlider(R.drawable.logo_two))
        listItems1.add(GetStartedSlider(R.drawable.logo_three))

        listItems2.add(GetStartedSlider(R.drawable.struct_one))
        listItems2.add(GetStartedSlider(R.drawable.struct_two))
        listItems2.add(GetStartedSlider(R.drawable.struct_three))
        listItems2.add(GetStartedSlider(R.drawable.struct_four))
        listItems2.add(GetStartedSlider(R.drawable.struct_five))
        listItems2.add(GetStartedSlider(R.drawable.struct_six))



        viewPagerLive1.postValue(listItems1)
        viewPagerLive2.postValue(listItems2)
    }
}