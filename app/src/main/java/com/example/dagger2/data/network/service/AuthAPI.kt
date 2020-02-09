package com.example.dagger2.data.network.service

import com.example.dagger2.data.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthAPI {
    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>
}
