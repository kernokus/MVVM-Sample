package com.example.diplomapp.api

import com.example.diplomapp.model.FlowerPOJO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlowService { //прокинуть в VM
    companion object {
        const val BASE_URL_PIXABAY="https://pixabay.com/api/"
        fun create(): FlowService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL_PIXABAY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(FlowService::class.java)
        }
    }

    @GET("?")
    suspend fun getFlowList(@Query("key") key: String, @Query("q") q: String, @Query("image_type") imageType: String): FlowerPOJO




}