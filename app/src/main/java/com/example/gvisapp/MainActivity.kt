package com.example.gvisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gvisapp.Food.AddFoodScreen
import com.example.gvisapp.Food.Food
import com.example.gvisapp.Food.FoodScreen
import com.example.gvisapp.ui.theme.GvisAppTheme

class MainActivity : ComponentActivity() {
    companion object{
        val foodlist = listOf<Food>(
            Food("라면",500f,15f,20f,10f),//dummy code for test
            Food("햄버거",50f,100f,40f,100f),
            Food("이름 겁나긴 햄버거띠",100f,100f,100f,100f)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GvisAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bottomValue = remember {
                        mutableStateOf(0)
                    }
                    NavHost(navController, "SCREEN") {
                        composable("SCREEN") {
                            MainScreen(bottomValue,navController)
                        }
                        composable("Food/{where}") {
                            FoodScreen(bottomValue,it.arguments?.getString("where")!!.toInt(),navController)
                        }
                        composable("AddFood/{where}") {
                            AddFoodScreen(bottomValue,it.arguments?.getString("where")!!.toInt(),navController)
                        }
                    }
                }
            }
        }
    }
}

