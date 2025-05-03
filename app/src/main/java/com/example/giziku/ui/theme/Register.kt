package com.example.giziku.ui.theme

import android.app.Application
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.giziku.model.User
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var spesialis by remember { mutableStateOf("") }
    var mataPelajaran by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("") }
    val roles = listOf("Orang Tua", "Tenaga Pendidikan", "Tenaga Kesehatan")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFAF6))
            .padding(24.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF015551))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome\nTo\nGiziKu!",
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input Fields
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015551),
                unfocusedBorderColor = Color(0xFF015551),
                cursorColor = Color(0xFF015551)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015551),
                unfocusedBorderColor = Color(0xFF015551),
                cursorColor = Color(0xFF015551)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("No. Handphone") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015551),
                unfocusedBorderColor = Color(0xFF015551),
                cursorColor = Color(0xFF015551)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015551),
                unfocusedBorderColor = Color(0xFF015551),
                cursorColor = Color(0xFF015551)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedRole,
                onValueChange = { selectedRole = it },
                label = { Text("Role") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF015551),
                    unfocusedBorderColor = Color(0xFF015551),
                    cursorColor = Color(0xFF015551)
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                roles.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(role) },
                        onClick = {
                            selectedRole = role
                            expanded = false
                        }
                    )
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || selectedRole.isEmpty()) {
                    errorMessage = "All fields are required."
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    errorMessage = "Invalid email address."
                } else if (password.length < 6) {
                    errorMessage = "Password must be at least 6 characters."
                } else {
                    userViewModel.registerUserDynamicRole(username, email, phone, password, selectedRole, tanggalLahir, spesialis, mataPelajaran, jenisKelamin)
                    errorMessage = ""
                    navController.navigate("login")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF015551))
        ) {
            Text(text = "Daftar", fontSize = 16.sp, color = Color.White)
        }
    }
}
