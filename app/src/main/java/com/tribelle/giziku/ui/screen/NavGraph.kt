package com.tribelle.giziku.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.giziku.ui.screens.EditProfileScreen
import com.example.giziku.ui.screens.TeacherHomeScreen
import com.tribelle.giziku.viewmodel.MainViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel // <-- ini penting!
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("role") { RoleScreen(navController) }

        // ✅ Tambahin viewModel di sini:
        composable("teacher_profile") {
            TeacherHomeScreen(navController = navController, viewModel = viewModel)
        }

        composable("edit_profile") {
            EditProfileScreen(navController = navController, viewModel = viewModel)
        }

        // dan screen lainnya...
    }
}
