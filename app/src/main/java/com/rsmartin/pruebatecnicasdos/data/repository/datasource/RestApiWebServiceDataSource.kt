package com.rsmartin.pruebatecnicasdos.data.repository.datasource

import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData
import retrofit2.Call
import retrofit2.http.GET

interface RestApiWebServiceDataSource {

    @GET("/resource/hma6-9xbg")
    fun getWebServiceDatas(): Call<List<WebServiceData>>

}