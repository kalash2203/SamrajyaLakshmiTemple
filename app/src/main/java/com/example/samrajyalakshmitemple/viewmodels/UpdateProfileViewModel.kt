package com.example.samrajyalakshmitemple.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.EditProfileResponse
import com.example.samrajyalakshmitemple.models.UploadImageResponse
import com.example.samrajyalakshmitemple.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class UpdateProfileViewModel (val repo: Repository) : MyViewModel() {


    val uploadImageLiveData: LiveData<UploadImageResponse>
        get() = repo.uploadImageResponse

    val editProfileLiveData: LiveData<EditProfileResponse>
        get()= repo.editProfileResponse

    fun uploadImageResponse(image:MultipartBody.Part?)
    {
        viewModelScope.launch {
            repo.uploadImageResponse(image)
        }
    }

    fun editProfileResponse(email2:String, name:String?, email: String?, phone:String?, city:String?,
                            state:String?, country:String?, img:String?)
    {
        viewModelScope.launch {
            repo.editProfileResponse(email2, name, email, phone, city, state, country, img)
        }
    }

}