package com.rsmartin.pruebatecnicasdos.presentation.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity
import com.rsmartin.pruebatecnicasdos.data.repository.UserRepository
import com.rsmartin.pruebatecnicasdos.presentation.model.LoggedInUserView
import com.rsmartin.pruebatecnicasdos.presentation.model.LoginResult

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(application)
    val loginResult = MutableLiveData<LoginResult>()

    fun login(userEmail: String, password: String) {

        if (!isUserNameValid(userEmail)) {
            loginResult.value =
                LoginResult(
                    userEmailError = R.string.invalid_username
                )
        } else if (!isPasswordValid(password)) {
            loginResult.value =
                LoginResult(
                    passwordError = R.string.invalid_password
                )
        } else {
            val result = UserEntity()
            result.email = userEmail

            loginResult.value =
                LoginResult(
                    success = LoggedInUserView(
                        result.id, result.name, result.surname, result.email, result.phoneNumber,
                        result.gender, result.userCode, result.userType
                    )
                )
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun insert(userList: ArrayList<UserEntity>) {
        for (user in userList) {
            userRepository.insertUser(user)
        }
    }

    fun getUser(email: String): UserEntity? {
        return userRepository.findUser(email)
    }

}