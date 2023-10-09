package com.example.gvisapp.food

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gvisapp.composable.util.BottomNavBar
import com.example.gvisapp.food.cardCompo.AddNewFoodScreen_firstCard
import com.example.gvisapp.food.cardCompo.AddNewFoodScreen_secondCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AddNewFoodScreen(bottomValue: MutableState<Int>, where: Int?, navController: NavController){

    val pagerState = rememberPagerState() {
        5
    }
    Scaffold(// 제목과 메뉴아이콘을 표시하는 탑 네비게이션 입니다.
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CHAT FIT")
                },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null, Modifier.fillMaxSize())
                    }
                }
            )
        },
        bottomBar = {//바텀 네비게이션입니다.
            BottomNavBar(bottomValue,navController = navController)
        },
    ) { innerPadding ->//메인 스크린 ui구현부 입니다.
            Box(modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxSize()) {
                val navControllerFoodAdd = rememberNavController()
                val text = remember {
                    mutableStateOf("")
                }
                NavHost(navController = navControllerFoodAdd, "FoodName"){
                    composable("FoodName"){
                        AddNewFoodScreen_firstCard(text = text, pagerState = pagerState)
                    }
                }

                

            }
        
        

    }
}
