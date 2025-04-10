package com.tribelle.giziku.data.dao

import androidx.room.*
import com.tribelle.giziku.data.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM student WHERE className = :className")
    fun getStudentsByClass(className: String): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)
}