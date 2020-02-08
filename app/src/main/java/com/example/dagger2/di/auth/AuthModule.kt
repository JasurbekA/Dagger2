package com.example.dagger2.di.auth

import com.example.dagger2.data.network.service.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    companion object {
        @Provides
        @JvmStatic
        fun provideAuthApi(retrofit: Retrofit) : AuthAPI =
            retrofit.create(AuthAPI::class.java)
    }


}

