package com.example.dagger2.di


import com.example.dagger2.di.auth.AuthViewModelsModule
import com.example.dagger2.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

}
