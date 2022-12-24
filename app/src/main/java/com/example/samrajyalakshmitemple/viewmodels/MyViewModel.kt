package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samrajyalakshmitemple.utils.Event

open class MyViewModel() : ViewModel() {

    val message = MutableLiveData<Event<String>>()

    val _message : LiveData<Event<String>>
        get() = message

}