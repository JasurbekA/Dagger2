package com.example.dagger2.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    companion object {
        @JvmStatic
        @Provides
        fun provideTestString() : String = "Hello from dagger, appModule"
    }
}
