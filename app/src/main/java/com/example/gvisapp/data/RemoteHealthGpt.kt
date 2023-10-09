package com.example.gvisapp.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
var gson = GsonBuilder().setLenient().create()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://118.45.192.116:8080/v1/") // 기본 URL 설정
    .addConverterFactory(GsonConverterFactory.create(gson)) // JSON 파싱을 위한 Converter Factory 설정
    .build()

// API 서비스 인터페이스 생성
val apiService: HealthGptApi = retrofit.create(HealthGptApi::class.java)
