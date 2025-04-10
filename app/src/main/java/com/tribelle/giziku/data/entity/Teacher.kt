package com.tribelle.giziku.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher")
data class Teacher(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val subject: String,
    val gender: String,
    val photoUri: String? = null
)