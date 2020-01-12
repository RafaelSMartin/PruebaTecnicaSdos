package com.rsmartin.pruebatecnicasdos.data.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.rsmartin.pruebatecnicasdos.data.map.WebServiceMap
import com.rsmartin.pruebatecnicasdos.data.model.ItemEntity
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.WebServiceDao
import com.rsmartin.pruebatecnicasdos.presentation.internal.room.WebServiceDatabase

class WebServiceRepository(application: Application) {
    private val webServiceDao: WebServiceDao

    init {
        val database = WebServiceDatabase.getDatabase(application)
        webServiceDao = database.itemDao()
    }

    fun insertItem(webServiceData: WebServiceData) {
        var item = WebServiceMap.map(webServiceData)
        InsertItem(webServiceDao).execute(item)
    }

    fun findAllItems(): List<WebServiceData> {
        Log.d("DATABASE", "findAllItemsANTES")
        val mutableList = FindAllItemsAsyncTask(webServiceDao).execute().get()
        Log.d("DATABASE", "findAllItemsDESPUES")
        return WebServiceMap.map(mutableList)
    }

    private class InsertItem internal constructor(private val mAsynTaskDao: WebServiceDao) :
        AsyncTask<ItemEntity, Void, Void>() {
        override fun doInBackground(vararg params: ItemEntity): Void? {
            mAsynTaskDao.insertItem(params[0])
            return null
        }
    }

    private class FindAllItemsAsyncTask internal constructor(private val mAsyncTaskDao: WebServiceDao) :
        AsyncTask<Void, Void, MutableList<ItemEntity>>() {

        override fun doInBackground(vararg params: Void): MutableList<ItemEntity> {
            val result = mAsyncTaskDao.findAllItems()
            Log.d("DATABASE", "doInBackground"+result.toString())
            return result
        }
    }
}