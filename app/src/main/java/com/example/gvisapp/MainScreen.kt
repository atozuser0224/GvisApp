package com.example.gvisapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gvisapp.Food.card.FoodCard
import com.example.gvisapp.composable.util.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(bottomValue: MutableState<Int>,navController: NavController){

    Scaffold(// 제목과 메뉴아이콘을 표시하는 탑 네비게이션 입니다.
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CHAT FIT")
                },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null,Modifier.fillMaxSize())
                    }
                }
            )
        },
        bottomBar = {//바텀 네비게이션입니다.
            BottomNavBar(bottomValue,navController = navController)
        },
    ){ innerPadding ->//메인 스크린 ui구현부 입니다.



        Column(modifier = Modifier.padding(innerPadding)) {
            Divider()
            FoodCard(navController)
        }
    }
}

