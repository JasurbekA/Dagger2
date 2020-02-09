package com.example.dagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dagger2.ui.auth.AuthViewModel.UserAttemptLoginState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val _cachedUser = MutableLiveData<UserAttemptLoginState>()
    val userState: LiveData<UserAttemptLoginState>
        get() = _cachedUser


    fun authUserWithID(userState: UserAttemptLoginState) {
        _cachedUser.value = userState
        _cachedUser.value = UserAttemptLoginState.OnIdle
    }

    fun logOut() {
        _cachedUser.value = UserAttemptLoginState.OnUserNotAuthenticated
    }


}
