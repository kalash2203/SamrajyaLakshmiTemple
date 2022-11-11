package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.models.GetStartedSlider


class InformationCentresViewModel(): ViewModel() {

    val viewPagerLive1 = MutableLiveData<List<GetStartedSlider>>()


    fun setViewPagerList() {


        val listItems1 = ArrayList<GetStartedSlider>()




        listItems1.add(GetStartedSlider(R.drawable.logo_one))
        listItems1.add(GetStartedSlider(R.drawable.logo_two))
        listItems1.add(GetStartedSlider(R.drawable.logo_three))




        viewPagerLive1.postValue(listItems1)

    }
}