package com.rsmartin.pruebatecnicasdos.data.repository

import android.app.Application
import android.os.AsyncTask
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.UserDao
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.UserDatabase

class UserRepository(application: Application) {

    private val userDao: UserDao

    init {
        val database = UserDatabase.getDatabase(application)
        userDao = database.userDao()
    }

    fun insertUser(user: UserEntity) {
        InsertAsynTask(userDao).execute(user)
    }

    fun findUser(email: String): UserEntity? {
        return FindUserAsyncTask(userDao).execute(email).get()
    }

    fun findUserByName(name: String): UserEntity{
        return FindUserByNameAsyncTask(userDao).execute(name).get()
    }

    fun findAllUsers(): MutableList<UserEntity>{
        return FindAllUsersAsyncTask(userDao).execute().get()
    }

    private class InsertAsynTask internal constructor(private val mAsynTaskDao: UserDao) :
        AsyncTask<UserEntity, Void, Void>() {
        override fun doInBackground(vararg params: UserEntity): Void? {
            mAsynTaskDao.insertUser(params[0])
            return null
        }
    }

    private class FindUserAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) :
        AsyncTask<String, Void, UserEntity>() {

        override fun doInBackground(vararg params: String): UserEntity? {
            val result = mAsyncTaskDao.findUserByEmail(params[0])
            return result
        }
    }

    private class FindUserByNameAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) :
        AsyncTask<String, Void, UserEntity>() {

        override fun doInBackground(vararg params: String): UserEntity? {
            val result = mAsyncTaskDao.findUserByName(params[0])
            return result
        }
    }

    private class FindAllUsersAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) :
        AsyncTask<Void, Void, MutableList<UserEntity>>() {

        override fun doInBackground(vararg params: Void): MutableList<UserEntity> {
            val result = mAsyncTaskDao.findAllUsers()
            return result
        }
    }
}