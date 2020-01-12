package com.rsmartin.pruebatecnicasdos.presentation.internal.retrofit

import com.rsmartin.pruebasdos.presentation.internal.retrofit.RetrofitClient
import com.rsmartin.pruebatecnicasdos.data.repository.datasource.RestApiWebServiceDataSource

object ApiUtils {
    const val BASE_URL = "https://data.ct.gov/"

    val apiService: RestApiWebServiceDataSource
        get() = RetrofitClient.getClient(BASE_URL)!!.create(RestApiWebServiceDataSource::class.java)
}