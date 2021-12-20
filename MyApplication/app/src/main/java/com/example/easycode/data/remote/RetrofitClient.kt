package com.example.easycode.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder

import com.google.gson.Gson




class RetrofitClient {

    companion object{
        val BASEURL = "https://easycode.mx/"
    }

    private fun getDefaultOkhttpClient(): OkHttpClient{
        val okBuilder = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

        return okBuilder
    }

    fun getRetrofitApi(baseUrl: String, okBuilder: OkHttpClient = getDefaultOkhttpClient()): Retrofit{
        val gson = GsonBuilder()
            .setLenient()
            .create()
        var retrofitPrueba = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okBuilder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofitPrueba
    }

}