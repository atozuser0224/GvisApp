package com.example.gvisapp.test

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gvisapp.Food
import com.example.gvisapp.Today
import java.text.SimpleDateFormat
import java.util.Date

object TestRepository: ViewModel(),TodayRepositoryInterface {
    private val repository:MutableLiveData<HashMap<String,Today>> = MutableLiveData()
    @SuppressLint("SimpleDateFormat")
    override fun addToday(breakfast:Food?,lunch:Food?,dinner:Food?){
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy")
        val currentDateAndTime = sdf.format(Date())
        repository.value?.set(currentDateAndTime, Today(breakfast, lunch, dinner))
    }
    @SuppressLint("SimpleDateFormat")
    override fun getByDate(): Today? {
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy")
        val currentDateAndTime = sdf.format(Date())
        return repository.value?.get(currentDateAndTime)
    }

    override fun getAllDay(): HashMap<String,Today>? {
        return repository.value
    }

    override fun updateDay(day:String, today: Today){
        repository.value?.set(day,today)
    }
}
fun Today.getFoodByID(id:Int):Food?=when(id){
    0->this.breakfast
    1->this.lunch
    2->this.dinner
    else -> this.dinner
}

interface TodayRepositoryInterface{
    fun addToday(breakfast:Food?,lunch:Food?,dinner:Food?)
    fun getByDate(): Today?
    fun getAllDay() : HashMap<String,Today>?
    fun updateDay(day: String,today: Today)

}
