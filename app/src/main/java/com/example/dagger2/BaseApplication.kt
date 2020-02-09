package com.example.dagger2

import android.content.Context
import androidx.multidex.MultiDex
import com.example.dagger2.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
         DaggerAppComponent.builder().application(this).build()


}
