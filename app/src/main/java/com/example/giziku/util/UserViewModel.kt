package com.example.giziku.util

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.giziku.database.AppDatabase
import com.example.giziku.database.UserRepository
import com.example.giziku.model.ProfileOrangTua
import com.example.giziku.model.ProfileTenagaMedis
import com.example.giziku.model.ProfileTenagaPendidikan
import com.example.giziku.model.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val profileOrangTuaDao = AppDatabase.getDatabase(application).profileOrangTuaDao()
    private val profileTenagaPendidikanDao = AppDatabase.getDatabase(application).profileTenagaPendidikanDao()
    private val profileTenagaMedisDao = AppDatabase.getDatabase(application).profileTenagaMedisDao()

    init {
        userRepository = UserRepository(userDao, profileOrangTuaDao, profileTenagaPendidikanDao, profileTenagaMedisDao)
    }

    fun logout() {
        viewModelScope.launch {
            // Hanya hapus ID user, JANGAN hapus profil
            val sharedPrefs = getApplication<Application>().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
            sharedPrefs.edit().remove("currentUserId").apply()
        }
    }

    val currentUserId: Long?
        get() {
            val sharedPrefs = getApplication<Application>().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
            val id = sharedPrefs.getLong("currentUserId", -1L)
            return if (id != -1L) id else null
        }


    // Fungsi untuk register user
    fun registerUserDynamicRole(
        username: String, email: String, phone: String, password: String,
        role: String, tanggalLahir: String?, spesialis: String?, mataPelajaran: String?, jenisKelamin: String
    ) {
        val user = User(username = username, email = email, phone = phone, password = password, role = role)

        viewModelScope.launch {
            val userId = userRepository.insertUserAndGetId(user)

            when (role) {
                "Orang Tua" -> {
                    val profile = ProfileOrangTua(
                        userOrangTuaId = userId,
                        username = username,
                        email = email,
                        phone = phone,
                        tanggalLahir = tanggalLahir ?: "",
                        jenisKelamin = jenisKelamin
                    )
                    userRepository.insertUserAndProfile(profile)
                }
                "Tenaga Kesehatan" -> {
                    val profile = ProfileTenagaMedis(
                        userTenagaMedisId = userId,
                        username = username,
                        email = email,
                        phone = phone,
                        spesialis = spesialis ?: "",
                        jenisKelamin = jenisKelamin
                    )
                    userRepository.insertUserAndProfileTenagaMedis(profile)
                }
                "Tenaga Pendidikan" -> {
                    val profile = ProfileTenagaPendidikan(
                        userTenagaPendidikanId = userId,
                        username = username,
                        email = email,
                        phone = phone,
                        mataPelajaran = mataPelajaran ?: "",
                        jenisKelamin = jenisKelamin
                    )
                    userRepository.insertUserAndProfileTenagaPendidikan(profile)
                }
            }
        }

    }

    // Fungsi untuk login user
    fun loginUser(email: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email)
            if (user != null && user.password == password) {
                onResult(user)
            } else {
                onResult(null)
            }
        }
    }

    suspend fun getProfileOrangTuaByUserId(userId: Long): ProfileOrangTua? {
        return userRepository.getProfileOrangTuaByUserId(userId)
    }
    suspend fun getProfileTenagaPendidikanByUserId(userId: Long): ProfileTenagaPendidikan? {
        return userRepository.getProfileTenagaPendidikanByUserId(userId)
    }
    suspend fun getProfileTenagaMedisByUserId(userId: Long): ProfileTenagaMedis? {
        return userRepository.getProfileTenagaMedisByUserId(userId)
    }

    fun updateProfileOrangTua(profile: ProfileOrangTua) {
        viewModelScope.launch {
            userRepository.updateProfileOrangTua(profile)
        }
    }

    fun updateProfileTenagaPendidikan(profile: ProfileTenagaPendidikan) {
        viewModelScope.launch {
            userRepository.updateProfileTenagaPendidikan(profile)
        }
    }

    fun updateProfileTenagaMedis(profile: ProfileTenagaMedis) {
        viewModelScope.launch {
            userRepository.updateProfileTenagaMedis(profile)
        }
    }

    fun setCurrentUserId(userId: Long) {
        val sharedPrefs = getApplication<Application>().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putLong("currentUserId", userId).apply()
    }

    fun getCurrentUserId(): Long {
        val sharedPrefs = getApplication<Application>().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getLong("currentUserId", -1L)
    }

}
