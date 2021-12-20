package com.example.easycode.domain.repository

import com.example.easycode.data.remote.RetrofitClient
import com.example.easycode.data.remote.UserAPI
import com.example.easycode.domain.model.ApiResponse
import kotlinx.coroutines.coroutineScope

object UserRepository {

    suspend fun register(name: String, mail: String, pass: String, gender: Int, goal: Int, level: Int, weight: String, age: String
    ): ApiResponse = coroutineScope {
        var res = ApiResponse()

        try {
            val retrofit = RetrofitClient().getRetrofitApi(RetrofitClient.BASEURL)
            val userService = retrofit.create(UserAPI::class.java)

            val result = userService.getInfo(name,mail,pass,gender,goal,level,weight,age,"newUser")

            if (result.isSuccessful) {
                res = result.body()!!
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        res
    }

    suspend fun login(mail: String, pass: String): ApiResponse = coroutineScope {
        var res = ApiResponse()

        try {
            val retrofit = RetrofitClient().getRetrofitApi(RetrofitClient.BASEURL)
            val userService = retrofit.create(UserAPI::class.java)

            val result = userService.login(mail,pass)

            if (result.isSuccessful) {
                res = result.body()!!
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        res
    }

}