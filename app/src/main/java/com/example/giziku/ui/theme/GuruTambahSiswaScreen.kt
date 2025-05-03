package com.example.giziku.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GuruTambahSiswaScreen(navController: NavController) {
    var idSiswa by remember { mutableStateOf(TextFieldValue("")) }
    val kelasList = listOf("1A", "2A", "3A", "4A", "5A", "6A")
    var selectedKelas by remember { mutableStateOf(kelasList.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar sederhana
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("Tambah Siswa", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = idSiswa,
            onValueChange = { idSiswa = it },
            placeholder = { Text("Masukkan ID") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF004D40),
                unfocusedBorderColor = Color(0xFF004D40)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            border = BorderStroke(1.dp, Color(0xFF004D40)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Kelas", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                kelasList.forEach { kelas ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedKelas == kelas,
                            onClick = { selectedKelas = kelas },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF004D40)
                            )
                        )
                        Text(text = kelas)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Aksi untuk tambah siswa (e.g. simpan ke database)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.height(40.dp)
        ) {
            Text("Tambah", color = Color.White)
        }
    }
}