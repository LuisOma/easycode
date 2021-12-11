package com.example.easycode.data.remote

import com.example.easycode.domain.model.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @FormUrlEncoded
    @Headers(
        "Content-Type: application/x-www-form-urlencoded")
    @POST("test_general_app/webservice/controller_last.php")
    suspend fun getInfo(@Field("first_name") name: String,
                        @Field("email") mail: String,
                        @Field("password") pass: String,
                        @Field("gender") gender: Int,
                        @Field("objective") goal: Int,
                        @Field("level") level: Int,
                        @Field("weight") weight: String,
                        @Field("age") age: String,
                        @Field("funcion") funcion: String
                        ): Response<ApiResponse>
}
