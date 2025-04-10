package com.tribelle.giziku.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.tribelle.giziku.data.AppDatabase
import com.tribelle.giziku.data.entity.Student
import com.tribelle.giziku.data.entity.Teacher
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)

    // LiveData untuk data teacher yang diambil dari DAO
    val teacher: LiveData<Teacher?> = db.teacherDao().getTeacher().asLiveData()

    // Fungsi untuk menyimpan teacher baru
    fun saveTeacher(teacher: Teacher) = viewModelScope.launch {
        db.teacherDao().insert(teacher)
    }

    // Fungsi untuk mendapatkan list siswa berdasarkan kelas
    fun getStudentsByClass(className: String): LiveData<List<Student>> {
        return db.studentDao().getStudentsByClass(className).asLiveData()
    }

    // Fungsi untuk menyimpan data siswa
    fun saveStudent(student: Student) = viewModelScope.launch {
        db.studentDao().insert(student)
    }

    // Fungsi updateTeacher, sebaiknya lewat DAO agar data di database terupdate
    fun updateTeacher(updatedTeacher: Teacher) = viewModelScope.launch {
        db.teacherDao().updateTeacher(updatedTeacher)
    }
}
