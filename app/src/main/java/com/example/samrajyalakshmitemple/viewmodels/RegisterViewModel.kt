package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.EditProfileResponse
import com.example.samrajyalakshmitemple.models.ShowMyProfileResponse
import com.example.samrajyalakshmitemple.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val showMyProfileLiveData: LiveData<ShowMyProfileResponse>
        get() = repo.showMyProfileResponse

    val signUpLiveData: LiveData<EditProfileResponse>
        get()= repo.signUpResponse

    fun showMyProfileResponse(email: String)
    {
        viewModelScope.launch {
            repo.showMyProfileResponse(email)
        }
    }

    fun signUpUser(name: String, email: String)
    {
        viewModelScope.launch {
            repo.signUpUser(name, email)
        }
    }
}