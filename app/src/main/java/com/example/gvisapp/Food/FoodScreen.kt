package com.example.gvisapp.Food

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gvisapp.Food.card.AdviceCard
import com.example.gvisapp.Food.card.KcalCard
import com.example.gvisapp.Food.card.NatureCard

import com.example.gvisapp.composable.BottomNavBar
import com.example.gvisapp.composable.DrawPieGraph
import com.example.gvisapp.composable.FoodScreenText
import com.example.gvisapp.composable.MainText
import com.example.gvisapp.composable.NavRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun FoodScreen(bottomValue: MutableState<Int>, where: Int?, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FOOD")
                },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null,Modifier.fillMaxSize())
                    }
                }
            )
        },
        bottomBar = {
            BottomNavBar(bottomValue,navController = navController)
        },
    ){ innerPadding ->
        val state = rememberPagerState()
        val select = remember {
            mutableStateOf(where!!)
        }
        var start by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.padding(innerPadding)) {
            Divider(Modifier.fillMaxWidth())
            NavRow(select)
            Divider(Modifier.fillMaxWidth())
            HorizontalPager(count = 4, state = state, userScrollEnabled = false) {
                LazyColumn() {
                    item {

                        //변수

                        val perAnime: Float by animateFloatAsState(if (it == state.currentPage) 1f else 0.000f, animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        ))
                        //런치 이펙트
                        LaunchedEffect(select.value){
                            state.scrollToPage(select.value)
                        }
                        LaunchedEffect(Unit){
                            start = true
                        }

                        //ui구현

                        Text(text = "아주 멋진 식단을 가지고 계시군요",Modifier.padding(10.dp), fontSize = 24.sp)
                        KcalCard(perAnime = perAnime)
                        Text(text = "오늘 먹은 음식의 영양소에요",Modifier.padding(10.dp), fontSize = 24.sp)
                        NatureCard(perAnime = perAnime)
                        Text(text = "이런 음식은 어떠세요?",Modifier.padding(10.dp), fontSize = 24.sp)
                        Text(text = "당신을 위한 음식입니다.",Modifier.padding(horizontal = 10.dp, vertical = 0.dp), fontSize = 20.sp, color = Color.Gray)
                        AdviceCard(navController)
                        
                        
                        
                        
                        
                        Box(modifier = Modifier.height(300.dp))
                    }
                }
            }
        }
    }
}








