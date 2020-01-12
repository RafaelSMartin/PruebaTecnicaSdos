package com.rsmartin.pruebatecnicasdos.data.repository

import android.app.Application
import android.os.AsyncTask
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.TaskDao
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.TaskDatabase

class TaskRepository(application: Application) {

    private val taskDao: TaskDao

    init {
        val database = TaskDatabase.getDatabase(application)
        taskDao = database.taskDao()
    }

    fun insertTask(task: TaskEntity) {
        InsertAsynTask(taskDao).execute(task)
    }

    fun findTasksByEmail(email: String): MutableList<TaskEntity> {
        return FindAllTasksByEmailAsyncTask(taskDao).execute(email).get()
    }

    fun updateTask(task: TaskEntity) {
        ChangeTaskCompletedByIdAsyncTask(taskDao).execute(task)
    }


    private class InsertAsynTask internal constructor(private val mAsynTaskDao: TaskDao) :
        AsyncTask<TaskEntity, Void, Void>() {
        override fun doInBackground(vararg params: TaskEntity): Void? {
            mAsynTaskDao.insertTask(params[0])
            return null
        }
    }

    private class FindAllTasksByEmailAsyncTask internal constructor(private val mAsyncTaskDao: TaskDao) :
        AsyncTask<String, Void, MutableList<TaskEntity>>() {

        override fun doInBackground(vararg params: String): MutableList<TaskEntity> {
            val result = mAsyncTaskDao.findTaskByEmail(params[0])
            return result
        }
    }

    private class ChangeTaskCompletedByIdAsyncTask internal constructor(private val mAsynTaskDao: TaskDao) :
        AsyncTask<TaskEntity, Void, Void>() {
        override fun doInBackground(vararg params: TaskEntity): Void? {
            mAsynTaskDao.updateTask(params[0])
            return null
        }
    }

}