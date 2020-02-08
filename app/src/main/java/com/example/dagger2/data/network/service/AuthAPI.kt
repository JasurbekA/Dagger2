package com.example.dagger2.data.network.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthAPI {
    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Call<String>
}
