package com.example.giziku.ui.theme

import android.app.Application
import android.util.Log
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
import androidx.compose.material3.CardDefaults
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
import com.example.giziku.model.AnakEntity
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory


@Composable
fun OrangtuaHomeScreen(navController: NavController) {
    val context = LocalContext.current
    var anakList by remember { mutableStateOf<List<AnakEntity>>(emptyList()) }

    val application = context.applicationContext as Application
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))

    LaunchedEffect(Unit) {
        val currentUserId = userViewModel.getCurrentUserId()
        val fetchedAnak = userViewModel.getAnakByOrangTuaId(currentUserId)
        anakList = fetchedAnak
        Log.d("OrangtuaHomeScreen", "Jumlah anak ditemukan: ${fetchedAnak.size}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        // Menampilkan daftar anak
        if (anakList.isEmpty()) {
            Text(text = "Belum ada data anak.", fontSize = 14.sp)
        } else {
            anakList.forEach { anak ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate("detailanak/${anak.id}")}
                    ,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_placeholder),
                            contentDescription = "Foto Anak",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text("Nama Anak: ${anak.nama}", fontWeight = FontWeight.Bold)
                            Text("Tanggal Lahir: ${anak.tanggalLahir}", fontSize = 12.sp)
                            Text("Jenis Kelamin: ${anak.jenisKelamin}", fontSize = 12.sp)
                            Text("Kode Unik: ${anak.kodeUnik}", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tambah Anak Button
        Button(
            onClick = { navController.navigate("pendaftaran")},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369))
        ) {
            Text(text = "Tambah Anak", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MenuItem(R.drawable.profile_placeholder, "Profile") {
                        navController.navigate("profileorangtua")
                    }
                    MenuItem(R.drawable.ic_graph, "Grafik") {
                        navController.navigate("grafik")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MenuItem(R.drawable.tracing_nutrisi, "Tracker Nutrisi") {
                        navController.navigate("tracker")
                    }
                    MenuItem(R.drawable.kamera, "Kamera Gizi") {
                        navController.navigate("kamera")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Edukasi Gizi
        Text(
            text = "Edukasi Gizi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        repeat(3) {
            EdukasiCard()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun MenuItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun EdukasiCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.gizi), // Ganti sesuai asset
                contentDescription = "Gizi Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = "Gizi Yang Sehat", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(
                    text = "Berikanlah Anak Makan dengan Porsi Seimbang 2-3x Sehari...",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun DetailAnakScreen(
    anakId: Int,
    userViewModel: UserViewModel,
    onBack: () -> Unit
) {
    var anak by remember { mutableStateOf<AnakEntity?>(null) }

    LaunchedEffect(anakId) {
        anak = userViewModel.getAnakById(anakId)
    }

    anak?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(text = "Detail Anak", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Nama: ${it.nama}", fontSize = 18.sp)
            Text(text = "Tanggal Lahir: ${it.tanggalLahir}", fontSize = 18.sp)
            Text(text = "Jenis Kelamin: ${it.jenisKelamin}", fontSize = 18.sp)
            Text(text = "Kode Unik: ${it.kodeUnik}", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onBack) {
                Text("Kembali")
            }
        }
    } ?: run {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Memuat data anak...")
        }
    }
}
