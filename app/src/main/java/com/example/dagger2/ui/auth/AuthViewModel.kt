package com.example.dagger2.ui.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2.data.network.service.AuthAPI
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authAPI: AuthAPI
) : ViewModel() {

    fun test() = "This string coming from AuthViewModel"

    fun checkAuthApi() = if (authAPI == null)
        "Null API"
    else
        "Not Null"
}

/*



*/
