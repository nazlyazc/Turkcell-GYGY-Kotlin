package com.example.kullanici_listesi_uygulamasi_hw.data.repository

import com.example.kullanici_listesi_uygulamasi_hw.data.model.User
import com.example.kullanici_listesi_uygulamasi_hw.data.remote.ApiService

class UserRepository(private val apiService: ApiService) {
    // API'den kullanıcı listesini çeken fonksiyon
    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}