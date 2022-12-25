package com.example.samrajyalakshmitemple.repository

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samrajyalakshmitemple.di.NetworkModule
import com.example.samrajyalakshmitemple.di.NetworkModuleImageUpload
import com.example.samrajyalakshmitemple.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Repository {
    private val _donationRecordResponse = MutableLiveData<DonationRecordResponse>()
    val donationRecordResponse: LiveData<DonationRecordResponse>
        get() = _donationRecordResponse

    private val _makeAdminResponse = MutableLiveData<MakeAdminResponse>()
    val makeAdminResponse: LiveData<MakeAdminResponse>
        get() = _makeAdminResponse

    private val _myDonationRecordResponse = MutableLiveData<MyDonationRecordResponse>()
    val myDonationRecordResponse: LiveData<MyDonationRecordResponse>
        get() = _myDonationRecordResponse

    private val _removeUserResponse = MutableLiveData<RemoveUserResponse>()
    val removeUserResponse: LiveData<RemoveUserResponse>
        get() = _removeUserResponse

    private val _showMyProfileResponse = MutableLiveData<ShowMyProfileResponse>()
    val showMyProfileResponse: LiveData<ShowMyProfileResponse>
        get() = _showMyProfileResponse

    private val _showUserPanelRecordResponse = MutableLiveData<ShowUserPanelRecordResponse>()
    val showUserPanelRecordResponse: LiveData<ShowUserPanelRecordResponse>
        get() = _showUserPanelRecordResponse

    private val _uploadImageResponse = MutableLiveData<UploadImageResponse>()
    val uploadImageResponse: LiveData<UploadImageResponse>
        get() = _uploadImageResponse


    private val _editProfileResponse = MutableLiveData<EditProfileResponse>()
    val editProfileResponse: LiveData<EditProfileResponse>
        get() = _editProfileResponse



    suspend fun donationRecordResponse() {
        val result = NetworkModule.getNetworkModule().getApi().donationRecord()
        if (result.isSuccessful && result.body() != null) {
            _donationRecordResponse.postValue((result.body()))
        }
    }

    suspend fun makeAdminResponse(email: String, role: String) {
        val result = NetworkModule.getNetworkModule().getApi().makeAdmin(email, role)
        if (result.isSuccessful && result.body() != null) {
            _makeAdminResponse.postValue((result.body()))
        }
    }

    suspend fun myDonationRecordResponse(email: String) {
        val result = NetworkModule.getNetworkModule().getApi().myDonationRecord(email)
        if (result.isSuccessful && result.body() != null) {
            _myDonationRecordResponse.postValue((result.body()))
        }
    }

    suspend fun removeUserResponse(id: String) {
        val result = NetworkModule.getNetworkModule().getApi().removeUser(id)
        if (result.isSuccessful && result.body() != null) {
            _removeUserResponse.postValue((result.body()))
        }
    }

    suspend fun showMyProfileResponse(email: String) {
        val result = NetworkModule.getNetworkModule().getApi().showMyProfileDetails(email)
        if (result.isSuccessful && result.body() != null) {
            _showMyProfileResponse.postValue((result.body()))
        }
    }

    suspend fun showUserPanelRecordResponse() {
        val result = NetworkModule.getNetworkModule().getApi().showUserPanelDetails()
        if (result.isSuccessful && result.body() != null) {
            _showUserPanelRecordResponse.postValue((result.body()))
        }
    }

    suspend fun uploadImageResponse(image:MultipartBody.Part) {
        val result = NetworkModuleImageUpload.getNetworkModule().getApi().uploadImage(image = image)
        if (result.isSuccessful && result.body() != null) {
            _uploadImageResponse.postValue((result.body()))
        }
    }

    suspend fun editProfileResponse(email2:String,name:RequestBody,email: RequestBody,phone:RequestBody,city:RequestBody,state:RequestBody,country:RequestBody,img:MultipartBody.Part) {
        val result = NetworkModule.getNetworkModule().getApi().editProfile(email2,name,email,phone,city,state, country, img)
        if (result.isSuccessful && result.body() != null) {
            _editProfileResponse.postValue((result.body()))
        }
    }
}

