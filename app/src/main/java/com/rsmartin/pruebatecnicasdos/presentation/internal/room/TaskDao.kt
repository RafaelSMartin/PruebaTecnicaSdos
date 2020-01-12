package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM task_table WHERE emailUser=:email")
    fun findTaskByEmail(email: String): MutableList<TaskEntity>

    @Update
    fun updateTask(task: TaskEntity)
}