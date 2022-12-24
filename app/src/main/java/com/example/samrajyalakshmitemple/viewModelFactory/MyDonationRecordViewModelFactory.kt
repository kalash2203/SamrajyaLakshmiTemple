package com.example.samrajyalakshmitemple.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.viewmodels.MyDonationRecordViewModel


@Suppress("UNCHECKED_CAST")
class MyDonationRecordViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyDonationRecordViewModel(repository) as T
    }
}