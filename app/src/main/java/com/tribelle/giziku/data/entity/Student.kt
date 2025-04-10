package com.tribelle.giziku.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val birthDate: String,
    val gender: String,
    val className: String,
    val photoUri: String? = null
)