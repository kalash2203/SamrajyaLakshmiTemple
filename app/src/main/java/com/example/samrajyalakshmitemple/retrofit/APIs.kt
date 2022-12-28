package com.example.samrajyalakshmitemple.retrofit

import com.example.samrajyalakshmitemple.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface APIs {

    @FormUrlEncoded
    @PUT("user/admin/{email}")
    suspend fun changeRole(
        @Path("email")email:String?,
        @Field("role") role: String
    ): Response<MakeAdminResponse>

    @DELETE("user/{id}")
    suspend fun removeUser(
        @Path("id")id:String?
    ): Response<RemoveUserResponse>

    @PUT("user/{email}")
    suspend fun showMyProfileDetails(
        @Path("email") email:String?
    ): Response<ShowMyProfileResponse>

    @GET("user")
    suspend fun showUserPanelDetails(): Response<ShowUserPanelRecordResponse>

    @GET("donation/{email}")
    suspend fun myDonationRecord(
        @Path("email") email:String
    ): Response<MyDonationRecordResponse>

    @GET("donation")
    suspend fun donationRecord(): Response<DonationRecordResponse>

    @FormUrlEncoded
    @PATCH("user/{email}")
    suspend fun editProfile(
        @Path("email")email2:String,
        @Field("name")name:String?,
        @Field("email")email:String?,
        @Field("phone")phone:String?,
        @Field("city")city:String?,
        @Field("state")state:String?,
        @Field("country")country:String?,
        @Field("img") img: String?
    ): Response<EditProfileResponse>


    @FormUrlEncoded
    @PATCH("user/{email}")
    suspend fun signUpUser(
        @Path("email")email2:String,
        @Part("name")name:String,
    ): Response<EditProfileResponse>

}