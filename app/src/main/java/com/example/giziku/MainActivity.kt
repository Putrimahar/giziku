package com.example.giziku

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giziku.ui.theme.Awal
import com.example.giziku.ui.theme.DetailAnakScreen
import com.example.giziku.ui.theme.EditProfileScreen
import com.example.giziku.ui.theme.EdukasiGiziMedis
import com.example.giziku.ui.theme.GrafikScreen
import com.example.giziku.ui.theme.GuruLaporanGizianakScreen
import com.example.giziku.ui.theme.GuruTambahSiswaScreen
import com.example.giziku.ui.theme.Login
import com.example.giziku.ui.theme.MedisHomeScreen
import com.example.giziku.ui.theme.OrangtuaEditProfileScreen
import com.example.giziku.ui.theme.OrangtuaHomeScreen
import com.example.giziku.ui.theme.OrangtuaPendaftarananakScreen
import com.example.giziku.ui.theme.OrangtuaProfileScreen
import com.example.giziku.ui.theme.ProfileMedis
import com.example.giziku.ui.theme.Register
import com.example.giziku.ui.theme.Role
import com.example.giziku.ui.theme.TeacherHomeScreen
import com.example.giziku.ui.theme.TeacherProfileEditScreen
import com.example.giziku.ui.theme.TeacherProfileScreen
import com.example.giziku.ui.theme.TeacherStudentListScreen
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val application = applicationContext as Application
            val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))


            NavHost(navController, startDestination = "awal") {
                composable("awal") { Awal(navController) }
                composable("login") { Login(navController) }
                composable("register") { Register(navController) }
                composable("role") { Role(navController) }

                composable("teacherhomescreen") { TeacherHomeScreen(navController) }
                composable("teacherprofilescreen") { TeacherProfileScreen(navController) }
                composable("teacherprofileeditscreen") { TeacherProfileEditScreen(navController) }
                composable("teacherstudentlistscreen") { TeacherStudentListScreen(navController) }


                composable("home") { MedisHomeScreen(navController) } // Pastikan ini benar
                composable("edit_profile") { EditProfileScreen(navController) }
                composable("profile_medis") { ProfileMedis(navController) }
                composable("grafik") { GrafikScreen(navController) }
                composable("edukasi_gizi") { EdukasiGiziMedis(navController) }
                composable("tambahsiswa") { GuruTambahSiswaScreen(navController) }
                composable("gurulaporangizianak") { GuruLaporanGizianakScreen() }

                composable("homeorangtua") { OrangtuaHomeScreen(navController) } // Pastikan ini benar
                composable("pendaftaran") { OrangtuaPendaftarananakScreen(navController) } // Pastikan ini benar
                composable("profileorangtua") { OrangtuaProfileScreen(navController) } // Pastikan ini benar
                composable("editprofileorangtua") { OrangtuaEditProfileScreen(navController) } // Pastikan ini benar
                composable("detailAnak") { OrangtuaEditProfileScreen(navController) } // Pastikan ini benar

                composable("detailanak/{id}") { backStackEntry ->
                    val anakId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                    DetailAnakScreen(
                        anakId = anakId,
                        userViewModel = viewModel(), // atau pakai remember / inject sesuai kebutuhanmu
                        onBack = { navController.popBackStack() }
                    )
                }


            }
        }
    }
}
