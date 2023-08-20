package com.example.gvisapp.Food

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gvisapp.Food.card.AdviceCard
import com.example.gvisapp.Food.card.KcalCard
import com.example.gvisapp.Food.card.NatureCard
import com.example.gvisapp.composable.BottomNavBar
import com.example.gvisapp.composable.JamoUtils
import com.example.gvisapp.composable.MainText
import com.example.gvisapp.composable.NavRow
import com.example.gvisapp.composable.SegmentButton
import com.example.gvisapp.composable.isEqualKorean
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun AddFoodScreen(bottomValue: MutableState<Int>, where: Int?, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ADD FOOD")
                },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null, Modifier.fillMaxSize())
                    }
                }
            )
        },
        bottomBar = {
            BottomNavBar(bottomValue, navController = navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            //더미데이터 생성
            val foodList = listOf<Food>(
                Food("햄버거", 0f, 0f, 0f, 0f),
                Food("스파게티", 0f, 0f, 0f, 0f),
                Food("짜장면", 0f, 0f, 0f, 0f),
                Food("햄버거", 0f, 0f, 0f, 0f),
                Food("스파게티", 0f, 0f, 0f, 0f),
                Food("짜장면", 0f, 0f, 0f, 0f)
            )
            val scoreList = remember {
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0)
            }
            //변수를 저장하는곳
            val select = remember {
                mutableStateOf(0)
            }
            val search = remember {
                mutableStateOf("")
            }
            var sortedlist = remember {
                mutableListOf<Food>()
            }

            //ui를 구현하는곳
            Divider()
            NavRow(
                select = select,
                listOf("아침", "점심", "저녁"),
                listOf(
                    Icons.Default.FreeBreakfast,
                    Icons.Default.LunchDining,
                    Icons.Default.DinnerDining
                )
            )
            Divider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(Modifier.align(TopStart)) {
                    MainText(text = "드신 음식이 있나요?", icon = Icons.Default.FoodBank, onclick = null)
                    Text(
                        text = "아래 리스트에서 선택해주세요!",
                        Modifier
                            .padding(bottom = 20.dp, start = 20.dp),
                        fontSize = 23.sp,
                        color = Color.Gray
                    )
                    Row() {
                        TextField(
                            value = search.value,
                            onValueChange = {
                                search.value = it
                                foodList.forEachIndexed { index, food ->  //per food
                                    scoreList[index] = search.value.isEqualKorean(food.name)
                                }
                                sortedlist = foodList.mapIndexed{ index, food ->  index to food}.sortedWith(compareBy { scoreList[it.first] }).map { it.second }
                                    .toMutableList()
                            },
                            Modifier
                                .fillMaxWidth(0.8f)
                                .height(60.dp)
                                .padding(5.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default
                        )
                        IconButton(
                            onClick = {},
                            Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(5.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                        }
                    }



                    LazyColumn(
                        Modifier
                            .padding()
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(10.dp)
                    ) {
                        itemsIndexed(foodList.mapIndexed{ index, food ->  index to food}.filter { scoreList[it.first] > 0 }.sortedWith(compareBy { scoreList[it.first] }).reversed()) { index, food ->//Card list item
                            if (true){
                                ElevatedCard(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .padding(horizontal = 0.dp, vertical = 2.dp),
                                    shape = RoundedCornerShape(0.dp)
                                ) {
                                    Box(Modifier.fillMaxSize()) {
                                        Text(
                                            text = "${food.second.name} , ${scoreList[food.first]} ${JamoUtils.split(food.second.name)}",
                                            Modifier.align(CenterStart)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Button(
                    onClick = {}, modifier = Modifier
                        .align(BottomEnd)
                        .padding(10.dp)
                ) {
                    Text(text = "다음으로")
                }

            }
        }
    }
}





