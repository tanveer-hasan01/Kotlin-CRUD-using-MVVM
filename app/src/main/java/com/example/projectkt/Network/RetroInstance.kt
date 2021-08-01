package com.example.projectkt.Network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        val baseurl = "https://gorest.co.in/public-api/"


        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level=(HttpLoggingInterceptor.Level.BODY)
            val client=OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        }


    }
}