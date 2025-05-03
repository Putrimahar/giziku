package com.example.giziku.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuruLaporanGizianakScreen() {
    var selectedKelas by remember { mutableStateOf("2A") }
    var selectedWaliKelas by remember { mutableStateOf("Himawan, S.Pd") }

    val kelasList = listOf("1A", "1B", "2A", "2B", "3A")
    val waliKelasList = listOf("Himawan, S.Pd", "Rina, M.Pd", "Budi, S.Pd")

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Data Siswa") },
            navigationIcon = {
                IconButton(onClick = { /* handle back */ }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ReadOnlyTextField(label = "Nama Lengkap", value = "Putri Maharani")
        ReadOnlyTextField(label = "NISN", value = "114476476")
        ReadOnlyTextField(label = "Tanggal Lahir", value = "Kamis, 17 Maret 2016")
        ReadOnlyTextField(label = "Jenis Kelamin", value = "Perempuan")
        ReadOnlyTextField(label = "Golongan Darah", value = "A")

        DropdownField(
            label = "Kelas",
            options = kelasList,
            selectedOption = selectedKelas,
            onOptionSelected = { selectedKelas = it }
        )

        DropdownField(
            label = "Wali Kelas",
            options = waliKelasList,
            selectedOption = selectedWaliKelas,
            onOptionSelected = { selectedWaliKelas = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Simpan perubahan */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Grafik Perkembangan", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFDFF5F4), shape = RoundedCornerShape(10.dp))
                .padding(8.dp)
        ) {
            Text("Tinggi Badan Sesuai Usia", modifier = Modifier.align(Alignment.Center))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun ReadOnlyTextField(label: String, value: String) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}