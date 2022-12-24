package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.MyDonationRecordResponse
import com.example.samrajyalakshmitemple.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyDonationRecordViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val myDonationRecordLiveData: LiveData<MyDonationRecordResponse>
        get() = repo.myDonationRecordResponse

    fun MyDonationRecordResponse(email:String)
    {
        viewModelScope.launch {
            repo.myDonationRecordResponse(email)
        }
    }
}