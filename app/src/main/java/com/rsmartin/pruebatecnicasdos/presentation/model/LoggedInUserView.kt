package com.rsmartin.pruebatecnicasdos.presentation.model

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    var id: Int = 0,            // 0
    var name: String = "",      // Rafael
    var surname: String = "",   // Martin
    var email: String = "",     // rsmartin.dev@gmail.com
    var phoneNumber: Int = 0,   // 999123456
    var gender: String = "",    // Sexo
    var userCode: String = "",  // Nif por ejemplo
    var userType: String = "",  // admin o tecnico
    var userAbilityReponder: Boolean = false,
    var userAbilityCharge: Boolean = false,
    var userAbilityWrap: Boolean = false,
    var userAbilityEtc: Boolean = false
)
