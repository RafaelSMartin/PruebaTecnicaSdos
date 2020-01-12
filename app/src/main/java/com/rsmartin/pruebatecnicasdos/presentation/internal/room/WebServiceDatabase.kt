package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.data.model.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class WebServiceDatabase : RoomDatabase() {

    abstract fun itemDao(): WebServiceDao

    companion object {
        private lateinit var context: Context

        private val database: WebServiceDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                context,
                WebServiceDatabase::class.java,
                Constants.databaseNameWebService
            ).build()
        }

        fun getDatabase(context: Context): WebServiceDatabase {
            Companion.context = context.applicationContext
            return database
        }
    }
}