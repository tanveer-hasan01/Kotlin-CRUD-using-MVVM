package com.example.projectkt.Network

import com.example.projectkt.ModelData.ModelUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetroService {

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUserList(): Call<ModelUser>

}