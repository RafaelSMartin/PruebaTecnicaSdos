package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private lateinit var context: Context

        private val database: TaskDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context, TaskDatabase::class.java, Constants.databaseNameTask)
                .build()
        }

        fun getDatabase(context: Context): TaskDatabase {
            Companion.context = context.applicationContext
            return database
        }
    }
}