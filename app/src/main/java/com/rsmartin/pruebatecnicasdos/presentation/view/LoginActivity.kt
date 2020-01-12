package com.rsmartin.pruebatecnicasdos.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rsmartin.pruebatecnicasdos.Constants
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity
import com.rsmartin.pruebatecnicasdos.presentation.model.LoggedInUserView
import com.rsmartin.pruebatecnicasdos.presentation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userEmail: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpView()

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        insertData()

        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.userEmailError != null) {
                userEmail.error = getString(loginResult.userEmailError)
            }
            if (loginResult.passwordError != null) {
                password.error = getString(loginResult.passwordError)
            }
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
        })

    }

    private fun insertData() {
        var userList = ArrayList<UserEntity>()
        var user1 = UserEntity(
            0, "Ruben", "Garcia", "rgarcia@gmail.com",
            111123456, "Masculino", "0123", "admin",
            true, true, true, true
        )
        userList.add(user1)

        var user2 = UserEntity(
            0, "Rafael", "Martin", "rafaels.martin.dev@gmail.com",
            222123456, "Masculino", "01234", "tecnico",
            false, true, false, false
        )
        userList.add(user2)

        var user3 = UserEntity(
            0, "Sarah", "Lopez", "slopez@gmail.com",
            333123456, "Femenino", "012345", "tecnico",
            true, false, true, false
        )
        userList.add(user3)
        loginViewModel.insert(userList)

    }

    private fun setUpView() {
        userEmail = findViewById(R.id.email)
        userEmail.setText("rafaels.martin.dev@gmail.com")
        password = findViewById(R.id.password)
        password.setText("12345678")
        login = findViewById(R.id.login)
        initLoginListener()
        loading = findViewById(R.id.loading)
    }

    private fun initLoginListener() {
        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(userEmail.text.toString(), password.text.toString())
        }
    }

    private fun updateUiWithUser(userSuccess: LoggedInUserView) {
        val user = loginViewModel.getUser(userSuccess.email)

        if (user == null) {
            showLoginFailed(R.string.login_failed)
            return
        }

        //Alamcenar login en prefs
        val sharedPreference = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("UserEmail", user.email)
        editor.apply()

        if (user.userType == "admin") {
            var intent = Intent(this, AdminActivity::class.java)
            intent.putExtra(Constants.USER_EMAIL, user.email)
            intent.putExtra(Constants.USER_NAME, user.name)
            startActivity(intent)
        } else {
            var intent = Intent(this, TecniActivity::class.java)
            intent.putExtra(Constants.USER_EMAIL, user.email)
            intent.putExtra(Constants.USER_NAME, user.name)
            startActivity(intent)
        }
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}
