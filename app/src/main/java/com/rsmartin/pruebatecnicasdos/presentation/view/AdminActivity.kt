package com.rsmartin.pruebatecnicasdos.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity
import com.rsmartin.pruebatecnicasdos.presentation.internal.adapter.WebServiceAdapter
import com.rsmartin.pruebatecnicasdos.presentation.viewmodel.AdminViewModel
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.item_layout.*

class AdminActivity : AppCompatActivity() {

    private lateinit var adminViewModel: AdminViewModel
    private lateinit var loading: ProgressBar
    private val takList = arrayListOf("Reponder", "Charge", "Wrap", "Etc")
    private var email: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        adminViewModel = ViewModelProviders.of(this).get(AdminViewModel::class.java)

        email = intent.getStringExtra(Constants.USER_EMAIL)

        setUpToolbar()
        setUpSpinners(takList, spinnerAddTask)
        setUpListenerSpinnerAddTask()
        setUpButtom()
        setUpLogOut()
        setUpWebServiceAdapter()

        adminViewModel.webDataResult.observe(this, Observer {
            val result = it ?: return@Observer

            loading.visibility = View.GONE

            if (result != null) {

                val sharedPreference = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putBoolean("InsertOK", true)
                editor.apply()

                rvWebService.adapter = WebServiceAdapter(result.webServiceDataList!!)
            }
        })
        startWebService()
    }

    private fun setUpLogOut() {
        logOut.setOnClickListener {
            finish()
        }
    }

    private fun startWebService() {
        loading.visibility = View.VISIBLE
        val sharedPreference = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (sharedPreference.getBoolean("InsertOK", false)) {
            adminViewModel.getAllDatas()
        } else {
            adminViewModel.getDatas()
        }
    }

    private fun setUpWebServiceAdapter() {
        rvWebService.layoutManager = LinearLayoutManager(this)
        rvWebService.adapter = null
    }

    private fun setUpButtom() {
        btCreateTask.setOnClickListener {
            //Mapear el task con los datos de la vista
            var task = TaskEntity()
            task.nameUser = spinnerAddUser.selectedItem.toString()
            task.typeTask = spinnerAddTask.selectedItem.toString()
            task.hoursTask = hourTask.text.toString()
            task.description = etDesc.text.toString()
            task.taskFinished = false
            task.emailUser = adminViewModel.findEmailByName(spinnerAddUser.selectedItem.toString())

            adminViewModel.insertTask(task)
            Toast.makeText(this, "Tarea almacenada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loading = findViewById(R.id.loading)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpListenerSpinnerAddTask() {
        spinnerAddTask.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val userList = adminViewModel.getAllUsers()
                val userListDynamic = ArrayList<String>()

                when (position) {
                    0 -> {
                        for (user in userList) {
                            if (user.userAbilityReponder) {
                                userListDynamic.add(user.name)
                            }
                        }
                    }
                    1 -> {
                        for (user in userList) {
                            if (user.userAbilityCharge) {
                                userListDynamic.add(user.name)
                            }
                        }
                    }
                    2 -> {
                        for (user in userList) {
                            if (user.userAbilityWrap) {
                                userListDynamic.add(user.name)
                            }
                        }
                    }
                    3 -> {
                        for (user in userList) {
                            if (user.userAbilityEtc) {
                                userListDynamic.add(user.name)
                            }
                        }
                    }
                }

                setUpSpinners(userListDynamic, spinnerAddUser)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setUpSpinners(stringList: ArrayList<String>, spinner: Spinner) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

}