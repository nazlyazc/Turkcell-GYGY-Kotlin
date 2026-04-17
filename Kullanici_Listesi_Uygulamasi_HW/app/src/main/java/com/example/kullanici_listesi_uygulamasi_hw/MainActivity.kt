package com.example.kullanici_listesi_uygulamasi_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kullanici_listesi_uygulamasi_hw.data.model.User
import com.example.kullanici_listesi_uygulamasi_hw.ui.screen.UserDetailScreen
import com.example.kullanici_listesi_uygulamasi_hw.ui.screen.UserListScreen
import com.example.kullanici_listesi_uygulamasi_hw.ui.theme.Kullanici_Listesi_Uygulamasi_HWTheme
import com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel.UserUiState
import com.example.kullanici_listesi_uygulamasi_hw.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. TEMA DURUMU: Kullanıcının seçimini burada tutuyoruz
            var isDarkMode by remember { mutableStateOf(false) }

            // Az önce güncellediğimiz Theme fonksiyonuna bu değeri gönderiyoruz
            Kullanici_Listesi_Uygulamasi_HWTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "userList"
                ) {
                    // 1. EKRAN: Kullanıcı Listesi
                    composable("userList") {
                        UserListScreen(
                            viewModel = viewModel,
                            isDarkMode = isDarkMode, // Tema durumunu gönder
                            onThemeChange = { isDarkMode = it }, // Değişim fonksiyonunu gönder
                            onUserClick = { secilenUser ->
                                navController.navigate("userDetail/${secilenUser.id}")
                            }
                        )
                    }

                    // 2. EKRAN: Kullanıcı Detay
                    composable(
                        route = "userDetail/{userId}",
                        arguments = listOf(navArgument("userId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getInt("userId")
                        val uiState by viewModel.filteredUiState.collectAsState()

                        if (uiState is UserUiState.Success) {
                            val userList = (uiState as UserUiState.Success).users
                            val selectedUser = userList.find { it.id == userId }

                            selectedUser?.let {
                                UserDetailScreen(
                                    user = it,
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}