package com.tribelle.giziku.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.giziku.ui.screens.EditProfileScreen
import com.example.giziku.ui.screens.TeacherHomeScreen
import com.tribelle.giziku.viewmodel.MainViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("role") { RoleScreen(navController) }

        composable("teacher_profile") {
            TeacherHomeScreen(navController, viewModel)
        }
        composable("edit_profile") {
            EditProfileScreen(navController, viewModel)
        }
    }
}
