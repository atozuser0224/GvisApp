package com.example.gvisapp.data

import com.google.gson.annotations.Expose

data class Result<T>(
    val code: Int,
    val data: T,
    val msg: String,
    val success: Boolean
)

data class UserData(
    val authorities: Map<String, Any>,
    val birth: String,
    val height: Int,
    val msrl: Int,
    val name: String,
    val roles: List<String>,
    val todays: List<TodayData>,
    val uid: String,
    val weight: Int
)

data class TodayData(
    val breakFast: Food,
    val day: String,
    val dinner: Food,
    val id: Int,
    val lunch: Food,
    val user: Map<String, Any>
)

data class Food(
    val day_selected: Int,
    val description: String,
    val etc_Foods: List<EtcFoodData>,
    val fat: Int,
    val id: Int,
    val kcal: Int,
    val name: String,
    val protein: Int,
    val sugar: Int,
    val total_selected: Int
)

data class EtcFoodData(
    val description: String,
    val id: Int,
    val name: String,
    val value: Int
)
data class MailRequest(

    val mail:String
)
data class SingleResult<T>(
    val code: Int,
    val data: T?,
    val msg: String?,
    val success: Boolean
)
data class UserSignInRequest(
    val email: String,
    val password: String
)