package com.rsmartin.pruebatecnicasdos.presentation.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceDataList
import com.rsmartin.pruebatecnicasdos.data.repository.TaskRepository
import com.rsmartin.pruebatecnicasdos.data.repository.UserRepository
import com.rsmartin.pruebatecnicasdos.data.repository.WebServiceRepository
import com.rsmartin.pruebatecnicasdos.presentation.internal.retrofit.ApiUtils.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(application)
    private val webServiceRepository: WebServiceRepository = WebServiceRepository(application)
    private val taskRepository: TaskRepository = TaskRepository(application)

    var webDataResult: MutableLiveData<WebServiceDataList> = MutableLiveData<WebServiceDataList>()

    fun getAllUsers(): MutableList<UserEntity> {
        return userRepository.findAllUsers()
    }

    fun getNameAllUsers(): ArrayList<String> {
        var allUsers = getAllUsers()
        var userList = ArrayList<String>()

        for (user in allUsers) {
            if (user.name != "") {
                userList.add(user.name)
            }
        }
        return userList
    }

    fun getDatas(){

        val webServiceDataList: WebServiceDataList? = WebServiceDataList(ArrayList<WebServiceData>())

        apiService.getWebServiceDatas().enqueue(object : Callback<List<WebServiceData>> {
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

    fun getAllDatas(){
        val webServiceDataList: WebServiceDataList? = WebServiceDataList(ArrayList<WebServiceData>())
        webServiceDataList?.webServiceDataList = webServiceRepository.findAllItems()
        webDataResult.value = webServiceDataList
    }

    fun insertTask(task: TaskEntity){
        taskRepository.insertTask(task)
    }

    fun findEmailByName(name : String) : String{
        return userRepository.findUserByName(name).email
    }

}