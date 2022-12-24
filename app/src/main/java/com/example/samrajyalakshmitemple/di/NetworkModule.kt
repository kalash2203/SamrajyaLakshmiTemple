package com.example.samrajyalakshmitemple.di

import com.example.samrajyalakshmitemple.retrofit.APIs
import com.example.samrajyalakshmitemple.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private var apiClient: NetworkModule? = null
    private var retrofit: Retrofit? = null



    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


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