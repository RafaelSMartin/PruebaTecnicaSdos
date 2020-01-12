package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private lateinit var context: Context

        private val database: UserDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context, UserDatabase::class.java, Constants.databaseName).build()
        }

        fun getDatabase(context: Context): UserDatabase {
            Companion.context = context.applicationContext
            return database
        }
    }
}