package com.example.samrajyalakshmitemple.retrofit

import com.example.samrajyalakshmitemple.models.*
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET("user/{email}")
    suspend fun myProfileDetails(
        @Path("email") email:String
    ): Response<ShowMyProfileResponse>

    @GET("user")
    suspend fun userPanelDetails(): Response<ShowUserPanelRecordResponse>

    @GET("donation/{email}")
    suspend fun myDonations(
        @Path("email") email:String
    ): Response<MyDonationRecordResponse>

    @GET("donation")
    suspend fun donationRecord(): Response<DonationRecordResponse>




}