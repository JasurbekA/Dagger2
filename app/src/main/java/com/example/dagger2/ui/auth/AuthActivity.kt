package com.example.dagger2.ui.auth


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.dagger2.R
import com.example.dagger2.ui.main.MainActivity
import com.example.dagger2.utils.extentions.toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        init()
    }

    private fun init() {
        loadImage()
        initVM()
        setOnClicks()
    }

    private fun loadImage() {
        login_logo.load(R.drawable.logo)
    }

    private fun initVM() {
        authViewModel = ViewModelProvider(
            this,
            providerFactory
        )[AuthViewModel::class.java]
    }

    private fun setOnClicks() {
        authBtnLogin.setOnClickListener {
            onLoginBtnClick()
        }
    }

    private fun onLoginBtnClick(){
        if (!TextUtils.isEmpty(authEDTUserId.text) && TextUtils.isDigitsOnly(authEDTUserId.text)){
            authViewModel.authUser(authEDTUserId.text.toString().toInt())
            observeLoginAttemptState()
        }else toast("Please enter valid input")
    }

    private fun observeLoginAttemptState() {
        authViewModel.userAttemptLoginState.observe(
            this,
            Observer {
                when (it) {
                    is AuthViewModel.UserAttemptLoginState.OnUserNotAuthenticated -> {
                        toast("Fail to Auth")
                    }
                    is AuthViewModel.UserAttemptLoginState.OnLoading -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    is AuthViewModel.UserAttemptLoginState.OnSuccess -> {
                       onAuthAttemptSuccess()
                    }
                    is AuthViewModel.UserAttemptLoginState.OnError -> {
                        toast("Invalid User ID entered ${it.err.localizedMessage}")
                        progress_bar.visibility = View.GONE
                    }
                }
            }
        )
    }

    private fun onAuthAttemptSuccess() {
        toast("Yes user is exist")
        progress_bar.visibility = View.GONE
        navigateToMainPage()
    }
    private fun navigateToMainPage() {
        val toMainPage = Intent(this, MainActivity::class.java)
        startActivity(toMainPage)
        finishAffinity()
    }
}
