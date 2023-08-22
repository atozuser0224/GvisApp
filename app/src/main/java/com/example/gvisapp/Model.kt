package com.example.gvisapp


data class Food(
    val name:String,
    val kcal:Float,
    val fats:Float,
    val sugar:Float,
    val protein : Float
)

data class Today(
    var id:Int,
    var day:String,
    var breakfast:Food?,
    var lunch:Food?,
    var dinner:Food?
)