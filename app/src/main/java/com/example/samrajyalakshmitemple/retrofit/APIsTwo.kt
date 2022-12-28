package com.example.samrajyalakshmitemple.retrofit

import com.example.samrajyalakshmitemple.models.UploadImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface APIsTwo {

    @Multipart
    @POST("1/upload")
    suspend fun uploadImage(
        @Query("key")key:String="68cb5fb5d48334a60f021c30aff06ada",
        @Part image: MultipartBody.Part?
    ): Response<UploadImageResponse>
}