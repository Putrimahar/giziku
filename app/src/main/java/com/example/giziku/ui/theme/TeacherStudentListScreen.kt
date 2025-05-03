package com.example.giziku.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TeacherStudentListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate("teacherhomescreen") }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Kelas 2B",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.DarkGray)

        LazyColumn {
            items(9) {
                StudentCard(
                    name = "Fitriya Nur Alyashifa",
                    birthDate = "Minggu, 14 September 2004",
                    gender = "Perempuan",
                    onClick = {navController.navigate("gurulaporangizianak") }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun StudentCard(
    name: String,
    birthDate: String,
    gender: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color(0xFF005F4B), shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            LabeledText(label = "Nama Lengkap", value = name)
            LabeledText(label = "Tanggal Lahir", value = birthDate)
            LabeledText(label = "Jenis Kelamin", value = gender)
        }
    }
}

@Composable
fun LabeledText(label: String, value: String) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$label : ")
            }
            append(value)
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
