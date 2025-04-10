package com.tribelle.giziku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tribelle.giziku.data.dao.StudentDao
import com.tribelle.giziku.data.dao.TeacherDao
import com.tribelle.giziku.data.entity.Student
import com.tribelle.giziku.data.entity.Teacher

@Database(entities = [Teacher::class, Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teacherDao(): TeacherDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "giziku_db"
                ).build().also { INSTANCE = it }
            }

        }
    }
}
