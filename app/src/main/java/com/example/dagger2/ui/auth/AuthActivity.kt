package com.example.dagger2.ui.auth


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.dagger2.R
import com.example.dagger2.data.User
import com.example.dagger2.utils.extentions.toast
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {


    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_logo.load(R.drawable.logo)

        authViewModel = ViewModelProvider(this, providerFactory)[AuthViewModel::class.java]

        println(authViewModel.test())

        val disposables = authViewModel.authUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object : Observer<User> {
                override fun onComplete() {
                    println("it is complete")
                }

                override fun onSubscribe(d: Disposable) {
                    println("it is subscribed")
                }

                override fun onNext(t: User) {
                    println("it is user name ${t.name}")
                }

                override fun onError(e: Throwable) {
                    println("it is err ${e.localizedMessage}")
                }

            })
    }
}
