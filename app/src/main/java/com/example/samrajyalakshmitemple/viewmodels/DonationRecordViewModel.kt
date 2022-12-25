package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.DonationRecordResponse
import com.example.samrajyalakshmitemple.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DonationRecordViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val donationLiveData: LiveData<DonationRecordResponse>
        get() = repo.donationRecordResponse

   fun donationRecordResponse()
   {
     viewModelScope.launch {
          repo.donationRecordResponse()
      }
   }
}