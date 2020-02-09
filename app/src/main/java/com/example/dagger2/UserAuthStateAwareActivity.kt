package com.example.dagger2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.example.dagger2.ui.auth.AuthActivity
import com.example.dagger2.ui.auth.AuthViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class UserAuthStateAwareActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentView())
        observeUserAuthState()
    }
    @LayoutRes
    abstract fun setContentView() : Int

    private fun observeUserAuthState() {
        sessionManager.userState.observe(
            this,
            Observer {
                if (it == AuthViewModel.UserAttemptLoginState.OnUserNotAuthenticated)
                    navigateToAuthScreen()
            }
        )
    }

    private fun navigateToAuthScreen() {
        val toAuthActivity = Intent(this, AuthActivity::class.java)
        startActivity(toAuthActivity)
        finishAffinity()
    }
}
