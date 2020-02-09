package com.example.dagger2.ui.main

import android.os.Bundle
import android.os.Handler
import com.example.dagger2.UserAuthStateAwareActivity
import com.example.dagger2.R
import com.example.dagger2.ui.auth.AuthViewModel

class MainActivity : UserAuthStateAwareActivity(){

    override fun setContentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        Handler().postDelayed({
            sessionManager.authUserWithID(AuthViewModel.UserAttemptLoginState.OnUserNotAuthenticated)
        },5000)
    }
}
