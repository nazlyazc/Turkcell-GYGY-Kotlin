package com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel

import com.example.kullanici_listesi_uygulamasi_hw.data.model.User

// Ödevde istenen 3 farklı durum (State) tanımı [cite: 695]
sealed interface UserUiState {
    object Loading : UserUiState
    data class Success(val users: List<User>) : UserUiState
    data class Error(val message: String) : UserUiState
}