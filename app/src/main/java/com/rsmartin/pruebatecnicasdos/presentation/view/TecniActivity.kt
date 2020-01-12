package com.rsmartin.pruebatecnicasdos.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.presentation.internal.adapter.TaskAdapter
import com.rsmartin.pruebatecnicasdos.presentation.internal.adapter.WebServiceAdapter
import com.rsmartin.pruebatecnicasdos.presentation.viewmodel.TecniViewModel
import kotlinx.android.synthetic.main.activity_tecni.*


class TecniActivity : AppCompatActivity() {

    private lateinit var tecniViewModel: TecniViewModel
    private var email: String = ""
    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tecni)

        tecniViewModel = ViewModelProviders.of(this).get(TecniViewModel::class.java)

        email = intent.getStringExtra(Constants.USER_EMAIL)!!
        name = intent.getStringExtra(Constants.USER_NAME)!!

        setUpToolbar()
        setUpWebServiceAdapter()
        setUpTaskAdapter()

        tecniViewModel.taskResult.observe(this, Observer {
            val result = it ?: return@Observer

            rvTaskTecni.adapter = TaskAdapter(result, application)
        })

        tecniViewModel.webDataResult.observe(this, Observer {
            val result = it ?: return@Observer

            if (result != null) {

                val sharedPreference = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putBoolean("InsertOK", true)
                editor.apply()

                rvWebService.adapter = WebServiceAdapter(result.webServiceDataList!!)
            }
        })

        startWebService()
        tecniViewModel.getAllTaskByEmail(email)
    }

    private fun startWebService() {
        val sharedPreference = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (sharedPreference.getBoolean("InsertOK", false)) {
            tecniViewModel.getAllDatas()
        } else {
            tecniViewModel.getDatas()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = name
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpWebServiceAdapter() {
        rvWebService.layoutManager = LinearLayoutManager(this)
        rvWebService.adapter = null
    }

    private fun setUpTaskAdapter() {
        rvTaskTecni.layoutManager = GridLayoutManager(this, 2)
        rvTaskTecni.adapter = null
    }

}