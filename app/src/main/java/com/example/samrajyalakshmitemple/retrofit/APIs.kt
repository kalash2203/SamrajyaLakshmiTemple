package com.example.samrajyalakshmitemple.retrofit

import com.example.samrajyalakshmitemple.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface APIs {

    @FormUrlEncoded
    @PUT("user/admin/{email}")
    suspend fun makeAdmin(
        @Path("email")email:String,
        @Field("role") role: String
    ): Response<MakeAdminResponse>

    @DELETE("user/{id}")
    suspend fun removeUser(
        @Path("id")id:String
    ): Response<RemoveUserResponse>

    @PUT("user/{email}")
    suspend fun showMyProfileDetails(
        @Path("email") email:String
    ): Response<ShowMyProfileResponse>

    @GET("user")
    suspend fun showUserPanelDetails(): Response<ShowUserPanelRecordResponse>

    @GET("donation/{email}")
    suspend fun myDonationRecord(
        @Path("email") email:String
    ): Response<MyDonationRecordResponse>

    @GET("donation")
    suspend fun donationRecord(): Response<DonationRecordResponse>

    @Multipart
    @PATCH("user/{email}")
    suspend fun editProfile(
        @Path("email")email2:String,
        @Part("name")name:RequestBody,
        @Part("email")email:RequestBody,
        @Part("phone")phone:RequestBody,
        @Part("city")city:RequestBody,
        @Part("state")state:RequestBody,
        @Part("country")country:RequestBody,
        @Part img: MultipartBody.Part
    ): Response<EditProfileResponse>

}