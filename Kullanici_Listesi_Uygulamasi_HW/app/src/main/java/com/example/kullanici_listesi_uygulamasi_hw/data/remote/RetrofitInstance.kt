package com.example.kullanici_listesi_uygulamasi_hw.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Temel API adresimiz [cite: 381]
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Retrofit ve Gson Converter kurulumu [cite: 382]
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}