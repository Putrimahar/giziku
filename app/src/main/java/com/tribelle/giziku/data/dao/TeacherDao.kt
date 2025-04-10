package com.tribelle.giziku.data.dao

import androidx.room.*
import com.tribelle.giziku.data.entity.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {
    @Query("SELECT * FROM teacher LIMIT 1")
    fun getTeacher(): Flow<Teacher?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teacher: Teacher)

    @Update
    suspend fun updateTeacher(teacher: Teacher)

}