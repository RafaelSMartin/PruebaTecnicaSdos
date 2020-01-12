package com.rsmartin.pruebatecnicasdos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameUser: String = "",
    var typeTask: String = "",
    var hoursTask: String = "",
    var dayTask: String = "",
    var emailUser: String = "",
    var description: String = "",
    var taskFinished: Boolean = false
)