package com.example.samrajyalakshmitemple.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samrajyalakshmitemple.di.NetworkModule
import com.example.samrajyalakshmitemple.models.*

class Repository {
    private val _donationRecordResponse= MutableLiveData<DonationRecordResponse>()
    val donationRecordResponse: LiveData<DonationRecordResponse>
        get()=_donationRecordResponse

    private val _makeAdminResponse= MutableLiveData<MakeAdminResponse>()
    val makeAdminResponse: LiveData<MakeAdminResponse>
        get()=_makeAdminResponse

    private val _myDonationRecordResponse= MutableLiveData<MyDonationRecordResponse>()
    val myDonationRecordResponse: LiveData<MyDonationRecordResponse>
        get()=_myDonationRecordResponse

    private val _removeUserResponse= MutableLiveData<RemoveUserResponse>()
    val removeUserResponse: LiveData<RemoveUserResponse>
        get()=_removeUserResponse

    private val _showMyProfileResponse= MutableLiveData<ShowMyProfileResponse>()
    val showMyProfileResponse: LiveData<ShowMyProfileResponse>
        get()=_showMyProfileResponse

    private val _showUserPanelRecordResponse= MutableLiveData<ShowUserPanelRecordResponse>()
    val showUserPanelRecordResponse: LiveData<ShowUserPanelRecordResponse>
        get()=_showUserPanelRecordResponse


    suspend fun donationRecordResponse()
    {
        val result= NetworkModule.getNetworkModule().getApi().donationRecord()
        if(result.isSuccessful && result.body()!=null)
        {
            _donationRecordResponse.postValue((result.body()))
        }}

        suspend fun makeAdminResponse(email:String,role:String)
        {
            val result= NetworkModule.getNetworkModule().getApi().makeAdmin(email, role)
            if(result.isSuccessful && result.body()!=null)
            {
                _makeAdminResponse.postValue((result.body()))
            } }

            suspend fun myDonationRecordResponse(email:String)
            {
                val result= NetworkModule.getNetworkModule().getApi().myDonationRecord(email)
                if(result.isSuccessful && result.body()!=null)
                {
                    _myDonationRecordResponse.postValue((result.body()))
                }}

                suspend fun removeUserResponse(id:String)
                {
                    val result= NetworkModule.getNetworkModule().getApi().removeUser(id)
                    if(result.isSuccessful && result.body()!=null)
                    {
                        _removeUserResponse.postValue((result.body()))
                    }}

                    suspend fun showMyProfileResponse(email: String)
                    {
                        val result= NetworkModule.getNetworkModule().getApi().showMyProfileDetails(email)
                        if(result.isSuccessful && result.body()!=null)
                        {
                            _showMyProfileResponse.postValue((result.body()))
                        }}

                        suspend fun showUserPanelRecordResponse()
                        {
                            val result= NetworkModule.getNetworkModule().getApi().showUserPanelDetails()
                            if(result.isSuccessful && result.body()!=null)
                            {
                                _showUserPanelRecordResponse.postValue((result.body()))
                            }}
    }

