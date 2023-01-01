package com.example.samrajyalakshmitemple.di

import com.example.samrajyalakshmitemple.retrofit.APIs
import com.example.samrajyalakshmitemple.ui.ApplicationClass
import com.example.samrajyalakshmitemple.utils.Constants
import com.example.samrajyalakshmitemple.utils.SavedPrefManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {
    private var apiClient: NetworkModule? = null
    private var retrofit: Retrofit? = null



    init {
       val token = "Bearer ${ApplicationClass().getToken()}"

        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }).build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Synchronized
    fun getNetworkModule() : NetworkModule {
        if (apiClient == null) {
            apiClient = NetworkModule
        }
        return apiClient as NetworkModule
    }

    fun getApi(): APIs {
        return retrofit!!.create(APIs::class.java)
    }
}