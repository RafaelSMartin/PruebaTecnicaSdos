package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rsmartin.pruebatecnicasdos.data.model.ItemEntity

@Dao
interface WebServiceDao {

    @Insert
    fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM items_table")
    fun findAllItems(): MutableList<ItemEntity>

}