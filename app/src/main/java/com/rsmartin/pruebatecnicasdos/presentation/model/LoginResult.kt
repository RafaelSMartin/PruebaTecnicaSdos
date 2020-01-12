package com.rsmartin.pruebatecnicasdos.presentation.model

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val userEmailError: Int? = null,
    val passwordError: Int? = null

)
