package com.rsmartin.pruebatecnicasdos.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceDataList
import com.rsmartin.pruebatecnicasdos.data.repository.TaskRepository
import com.rsmartin.pruebatecnicasdos.data.repository.WebServiceRepository
import com.rsmartin.pruebatecnicasdos.presentation.internal.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TecniViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository = TaskRepository(application)
    private val webServiceRepository: WebServiceRepository = WebServiceRepository(application)

    var webDataResult: MutableLiveData<WebServiceDataList> = MutableLiveData<WebServiceDataList>()
    var taskResult: MutableLiveData<MutableList<TaskEntity>> = MutableLiveData<MutableList<TaskEntity>>()


    fun getAllTaskByEmail(email : String): MutableList<TaskEntity>{
        taskResult.value =  taskRepository.findTasksByEmail(email)
        return taskRepository.findTasksByEmail(email)
    }

    fun getAllDatas(){
        val webServiceDataList: WebServiceDataList? = WebServiceDataList(ArrayList<WebServiceData>())
        webServiceDataList?.webServiceDataList = webServiceRepository.findAllItems()
        webDataResult.value = webServiceDataList
    }

    fun getDatas(){

        val webServiceDataList: WebServiceDataList? = WebServiceDataList(ArrayList<WebServiceData>())

        ApiUtils.apiService.getWebServiceDatas().enqueue(object : Callback<List<WebServiceData>> {
            override fun onResponse(call: Call<List<WebServiceData>>, response: Response<List<WebServiceData>>) {

                if(response.isSuccessful){
                    for(item in response.body()!!){
                        webServiceRepository.insertItem(item)
                    }
                }

                webServiceDataList?.webServiceDataList = response.body()
                webDataResult.value = webServiceDataList
            }

            override fun onFailure(call: Call<List<WebServiceData>>, t: Throwable) {
                webDataResult.value = null
            }
        })
    }
}