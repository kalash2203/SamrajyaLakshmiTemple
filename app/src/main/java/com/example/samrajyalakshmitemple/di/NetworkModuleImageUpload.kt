package com.example.samrajyalakshmitemple.di
import com.example.samrajyalakshmitemple.retrofit.APIs
import com.example.samrajyalakshmitemple.retrofit.APIsTwo
import com.example.samrajyalakshmitemple.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleImageUpload {
    private var apiClient: NetworkModuleImageUpload? = null
    private var retrofit: Retrofit? = null



    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_TWO)

            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Synchronized
    fun getNetworkModule() : NetworkModuleImageUpload {
        if (apiClient == null) {
            apiClient = NetworkModuleImageUpload
        }
        return apiClient as NetworkModuleImageUpload
    }

    fun getApi(): APIsTwo {
        return retrofit!!.create(APIsTwo::class.java)
    }
}