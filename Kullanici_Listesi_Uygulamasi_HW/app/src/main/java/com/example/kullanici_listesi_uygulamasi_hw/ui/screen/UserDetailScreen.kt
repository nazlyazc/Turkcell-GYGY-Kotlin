package com.example.kullanici_listesi_uygulamasi_hw.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kullanici_listesi_uygulamasi_hw.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: User, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(user.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("Kullanıcı Adı: ${user.username}", style = MaterialTheme.typography.bodyLarge)
            Text("Email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
            Text("Telefon: ${user.phone}", style = MaterialTheme.typography.bodyLarge)
            Text("Web Sitesi: ${user.website}", style = MaterialTheme.typography.bodyLarge)
            // İstersen buraya şirket veya adres bilgilerini de ekleyebilirsin
        }
    }
}