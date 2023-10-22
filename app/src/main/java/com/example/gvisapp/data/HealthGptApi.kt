package com.example.gvisapp.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface HealthGptApi {
    @POST("mail")
    fun getMail(
        @Body mail: MailRequest
    ): Call<CommonResult>

    @GET("users")
    fun getUsers():Call<Result<UserData>>
    @POST("signin")
    fun SignIn(
        @Body signInRequest: UserSignInRequest
    ):Call<SingleResult<String?>>
    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("equalMailcode")
    fun isMail(
        @Body isMailRequest: IsMailRequest
    ):Call<CommonResult>
}
