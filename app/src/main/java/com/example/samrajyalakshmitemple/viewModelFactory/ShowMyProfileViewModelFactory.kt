package com.example.samrajyalakshmitemple.viewModelFactory

import ShowMyProfileViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samrajyalakshmitemple.repository.Repository

@Suppress("UNCHECKED_CAST")
class ShowMyProfileViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShowMyProfileViewModel(repository) as T
    }
}