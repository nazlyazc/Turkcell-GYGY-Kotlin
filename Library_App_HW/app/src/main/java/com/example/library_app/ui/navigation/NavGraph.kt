package com.example.library_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.library_app.ui.screen.BorrowedBooksScreen
import com.example.library_app.ui.screen.HomeScreen
import com.example.library_app.ui.screen.LoginScreen
import com.example.library_app.ui.screen.RegisterScreen
import com.example.library_app.ui.screen.SplashScreen
import com.example.library_app.ui.viewmodel.AuthViewModel
import com.example.library_app.ui.viewmodel.BookViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    val authViewModel: AuthViewModel = viewModel()
    val bookViewModel: BookViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(
                authViewModel = authViewModel,
                onAuthenticated = { role ->
                    navController.navigate(Screen.Homepage.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onUnauthenticated = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = { role ->
                    navController.navigate(Screen.Homepage.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                authViewModel = authViewModel
            )
        }


        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                authViewModel = authViewModel
            )
        }

        composable(Screen.Homepage.route) {
            HomeScreen(
                authViewModel = authViewModel,
                bookViewModel = bookViewModel,
                onNavigateToBorrowedBooks = {
                    navController.navigate(Screen.BorrowedBooks.route)
                },

                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        // Geri tuşuna basınca tekrar ana sayfaya dönmemesi için geçmişi temizle
                        popUpTo(Screen.Homepage.route) { inclusive = true }
                    }
                }
            )
        }


        composable(Screen.BorrowedBooks.route) {
            BorrowedBooksScreen(
                authViewModel = authViewModel,
                bookViewModel = bookViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}