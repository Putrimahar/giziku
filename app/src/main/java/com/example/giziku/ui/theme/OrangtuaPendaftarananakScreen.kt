package com.example.giziku.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.giziku.R

@Composable
fun OrangtuaPendaftarananakScreen(navController: NavController) {
    var nama by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var beratBadan by remember { mutableStateOf("") }
    var tinggiBadan by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F6F2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) { // Kembali ke Home
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF127369)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Edit Profile", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Foto
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Upload Foto",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFF127369), CircleShape)
        )
        Text(
            text = "Unggah Foto",
            color = Color(0xFF127369),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Form
        CustomTextField(value = nama, onValueChange = { nama = it }, placeholder = "Nama Lengkap")
        CustomTextField(value = tanggalLahir, onValueChange = { tanggalLahir = it }, placeholder = "Tanggal Lahir")

        // Jenis Kelamin
        Text(
            text = "Jenis Kelamin",
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { jenisKelamin = "Perempuan" },
                border = BorderStroke(1.dp, Color(0xFF005F4B)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (jenisKelamin == "Perempuan") Color.White else Color(0xFF005F4B),
                    containerColor = if (jenisKelamin == "Perempuan") Color(0xFF005F4B) else Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("Perempuan")
            }

            OutlinedButton(
                onClick = { jenisKelamin = "Laki-laki" },
                border = BorderStroke(1.dp, Color(0xFF005F4B)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (jenisKelamin == "Laki-laki") Color.White else Color(0xFF005F4B),
                    containerColor = if (jenisKelamin == "Laki-laki") Color(0xFF005F4B) else Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("Laki-laki")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(value = beratBadan, onValueChange = { beratBadan = it }, placeholder = "Berat Badan (cm)")
        CustomTextField(value = tinggiBadan, onValueChange = { tinggiBadan = it }, placeholder = "Tinggi Badan (cm)")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Simpan data */ },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan", color = Color.White)
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF127369),
            unfocusedBorderColor = Color(0xFF127369)
        )
    )
}
