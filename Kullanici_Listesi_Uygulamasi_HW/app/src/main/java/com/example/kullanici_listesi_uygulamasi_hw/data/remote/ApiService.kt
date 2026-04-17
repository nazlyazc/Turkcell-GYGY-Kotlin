package com.example.kullanici_listesi_uygulamasi_hw.data.remote

import com.example.kullanici_listesi_uygulamasi_hw.data.model.User
import retrofit2.http.GET

// Retrofit ile API entegrasyonu için arayüz tanımlıyoruz [cite: 382]
interface ApiService {
    // JSONPlaceholder üzerindeki /users endpoint'inden veri çekiyoruz [cite: 381, 425]
    @GET("users")
    // Suspend fonksiyon kullanarak coroutine desteği sağlıyoruz [cite: 384, 426]
    suspend fun getUsers(): List<User>
}