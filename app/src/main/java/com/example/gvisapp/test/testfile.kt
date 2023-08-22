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
    private val repository:MutableLiveData<ArrayList<Today>> = MutableLiveData()
    var id=0
    @SuppressLint("SimpleDateFormat")
    override fun addToday(breakfast:Food?,lunch:Food?,dinner:Food?){
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy")
        val currentDateAndTime = sdf.format(Date())

        repository.value?.add(Today(id= id++ , day = currentDateAndTime , breakfast, lunch, dinner))
    }
    @SuppressLint("SimpleDateFormat")
    override fun getByDate(): Today? {
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy")
        val currentDateAndTime = sdf.format(Date())
        return repository.value?.find { it.day == currentDateAndTime }
    }

    override fun getAllDay(): MutableList<Today> {
        return repository.value!!
    }

    override fun updateDay(id:Int, today: Today){
        repository.value?.get(id)==today
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
    fun getAllDay() : MutableList<Today>
    fun updateDay(id: Int,today: Today)

}