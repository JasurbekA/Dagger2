package com.example.dagger2.ui.auth

import com.example.dagger2.data.network.service.AuthAPI
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authAPI: AuthAPI) {

    fun authUser (userID : Int) = authAPI.getUser(userID)
}
