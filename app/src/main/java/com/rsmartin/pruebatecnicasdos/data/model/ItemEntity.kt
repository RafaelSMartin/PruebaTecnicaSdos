package com.rsmartin.pruebatecnicasdos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var category: String = "",
    var item: String = "",
    var farmer_id: String = "",
    var farm_name: String = "",
    var zipcode: String = "",
    var phone1: String = "",
    var latitude: String = "",
    var longitude: String = "",
    var human_address: String = "",
    var business: String = "",
    var url: String = "WebSite no disponible"
)