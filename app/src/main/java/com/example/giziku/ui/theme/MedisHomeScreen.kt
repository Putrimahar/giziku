package com.example.giziku.ui.theme

import android.app.Application
import android.util.Log
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.giziku.R
import com.example.giziku.model.ProfileOrangTua
import com.example.giziku.model.ProfileTenagaMedis
import com.example.giziku.model.ProfileTenagaPendidikan
import com.example.giziku.model.User
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory


@Composable
fun MedisHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Tambahkan scroll
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome\nTo\nGiziKu!",
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 56.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        ProfileCard()

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("profile_medis") }, // Pindah ke Edit Profile
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Profile", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        MenuOptions(navController)
    }
}

@Composable
fun ProfileCard() {
    val context = LocalContext.current
    var profile by remember { mutableStateOf<ProfileTenagaMedis?>(null) }

    val application = context.applicationContext as Application
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))

    LaunchedEffect(Unit) {
        val currentUserId = userViewModel.getCurrentUserId()
        val fetchedProfile = userViewModel.getProfileTenagaMedisByUserId(currentUserId)
        profile = fetchedProfile
        Log.d("MedisHomeScreen", "Profile fetched successfully: ${fetchedProfile?.username}")
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            profile?.let {
                Column {
                    Text(
                        text = "Nama Lengkap: ${it.username}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Spesialis: ${it.spesialis}",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp) // Tambah jarak dari atas
                    )
                    Text(
                        text = "Jenis Kelamin: ${it.jenisKelamin}",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }?: run {
                // Jika data profile belum ada, tampilkan teks loading atau placeholder
                Text(text = "Memuat profil...", fontSize = 14.sp)
            }

//            Column {
//                Text(text = "Nama Lengkap :", fontSize = 14.sp)
//                Text(text = "Dr. Susanti Himawa, Sp.A", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(text = "Spesialis :", fontSize = 14.sp)
//                Text(text = "Spesialis Anak", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(text = "Jenis Kelamin :", fontSize = 14.sp)
//                Text(text = "Perempuan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//            }
        }
    }
}

@Composable
fun MenuOptions(navController: NavController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterHorizontally)
        ) {
            MenuItemMedis(iconRes = R.drawable.ic_education, label = "Edukasi Gizi") {
                navController.navigate("edukasi_gizi") // Navigasi ke halaman edukasi gizi
            }
            MenuItemMedis(iconRes = R.drawable.ic_graph, label = "Grafik") {
                navController.navigate("grafik") // Navigasi ke halaman grafik
            }
        }
    }
}

@Composable
fun MenuItemMedis(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() } // Agar bisa diklik
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}