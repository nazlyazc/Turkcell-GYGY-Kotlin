package com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kullanici_listesi_uygulamasi_hw.data.model.User
import com.example.kullanici_listesi_uygulamasi_hw.data.remote.RetrofitInstance
import com.example.kullanici_listesi_uygulamasi_hw.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository(RetrofitInstance.api)

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // Pull-to-refresh için loading durumu
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val filteredUiState: StateFlow<UserUiState> = combine(_uiState, _searchQuery) { state, query ->
        if (state is UserUiState.Success) {
            val filteredList = if (query.isEmpty()) {
                state.users
            } else {
                state.users.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.email.contains(query, ignoreCase = true)
                }
            }
            UserUiState.Success(filteredList)
        } else {
            state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserUiState.Loading)

    init { getUsers() }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    // Pull-to-refresh fonksiyonu
    fun refreshUsers() {
        viewModelScope.launch {
            _isRefreshing.value = true
            getUsers() // Verileri tekrar çek
            delay(1000) // Kullanıcıya yenilendiğini hissettirmek için kısa bir bekleme
            _isRefreshing.value = false
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            // Sadece ilk yüklemede ana loading gösterilsin diye kontrol ekleyebiliriz
            if (_uiState.value !is UserUiState.Success) {
                _uiState.value = UserUiState.Loading
            }
            try {
                val users = repository.getUsers()
                _uiState.value = UserUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.message ?: "Hata oluştu")
            }
        }
    }
}