package com.example.dagger2.ui.auth

import androidx.lifecycle.ViewModel
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun test() = "This string coming from AuthViewModel"

   fun authUser(userID : Int) = authRepository.authUser(userID)
}
