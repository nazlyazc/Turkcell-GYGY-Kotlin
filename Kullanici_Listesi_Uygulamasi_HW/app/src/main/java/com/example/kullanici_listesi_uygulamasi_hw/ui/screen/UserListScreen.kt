package com.example.kullanici_listesi_uygulamasi_hw.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kullanici_listesi_uygulamasi_hw.data.model.User
import com.example.kullanici_listesi_uygulamasi_hw.ui.components.UserItem
import com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel.UserUiState
import com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserViewModel,
    isDarkMode: Boolean, // MainActivity'den gelen tema durumu
    onThemeChange: (Boolean) -> Unit, // Tema değiştirme fonksiyonu
    onUserClick: (User) -> Unit
) {
    val uiState by viewModel.filteredUiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kullanıcı Listesi") },
                actions = {
                    // Tema Değiştirme İkonu ve Switch
                    Icon(
                        imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                        contentDescription = null
                    )
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { onThemeChange(it) },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Arama Çubuğu
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("İsim veya email ile ara...") },
                singleLine = true
            )

            // Pull-to-Refresh ve Liste
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = { viewModel.refreshUsers() },
                modifier = Modifier.fillMaxSize()
            ) {
                when (val state = uiState) {
                    is UserUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is UserUiState.Success -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(state.users) { user ->
                                UserItem(
                                    user = user,
                                    onClick = { onUserClick(user) }
                                )
                            }
                        }
                    }
                    is UserUiState.Error -> {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}