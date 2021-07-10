package com.example.projectkt.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit=null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private ApiClient() {}

    public static synchronized Retrofit instance()
    {

        if (retrofit==null)
        {

            int timeOut = 5 * 60;
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .build();

            retrofit=new Retrofit.Builder()

                    .baseUrl("https://dummy.restapiexample.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }



        return retrofit;
    }
}
