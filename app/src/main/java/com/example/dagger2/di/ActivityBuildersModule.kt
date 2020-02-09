package com.example.dagger2.di


import com.example.dagger2.di.auth.AuthModule
import com.example.dagger2.di.auth.AuthViewModelsModule
import com.example.dagger2.ui.auth.AuthActivity
import com.example.dagger2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeBaseActivity(): MainActivity

}
