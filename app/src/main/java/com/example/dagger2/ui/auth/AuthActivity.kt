package com.example.dagger2.ui.auth


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.dagger2.R
import com.example.dagger2.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {


    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var testString: String

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_logo.load(R.drawable.logo) {
            crossfade(true)
        }

        authViewModel = ViewModelProviders.of(this, providerFactory)[AuthViewModel::class.java]

        println("It is checking point : $testString")
        println(authViewModel.test())
    }
}
