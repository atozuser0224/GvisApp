package com.example.gvisapp.Food.card

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BreakfastDining
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.Food.Food
import com.example.gvisapp.Food.FoodQuaterCard
import com.example.gvisapp.R
import com.example.gvisapp.composable.DrawLineGraph
import com.example.gvisapp.composable.DrawPieGraph
import com.example.gvisapp.composable.DrawSlide
import com.example.gvisapp.composable.FoodScreenText
import com.example.gvisapp.composable.FoodTime
import com.example.gvisapp.composable.FoodTimeText
import com.example.gvisapp.composable.MainText
import com.example.gvisapp.composable.QuaterCard
import com.example.gvisapp.composable.SegmentButton
import com.example.gvisapp.ui.theme.BreakFast
import com.example.gvisapp.ui.theme.Dinner
import com.example.gvisapp.ui.theme.Lunch
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
val foodlist = listOf<Food>(
    Food(name = "햄버거",500f,25f,15f,10f),
    Food(name = "라면",600f,15f,5f,5f),
    Food(name = "스테이크",1000f,225f,115f,50f))

@Composable
fun FoodCard(navController: NavController){
    Column(){
        MainText(text = stringResource(id = R.string.food), Icons.Default.RestaurantMenu){
            navController.navigate("Food/${0}")
        }
        Text(text = "무엇을 얼마나 먹는지는 아셔야죠!",Modifier.padding(10.dp), fontSize = 24.sp)
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp)){
            QuaterCard(num = 1, onclick = {
                navController.navigate("Food/${1}")
            }) {
                FoodQuaterCard("라면",100,50, FoodTime.BREAKFAST)

            }
            QuaterCard(num = 2, onclick = {
                navController.navigate("Food/${2}")
            }) {
                FoodQuaterCard("햄버거",50,75, FoodTime.LUNCH)

            }
            QuaterCard(num = 3, onclick = {
                navController.navigate("Food/${3}")
            }) {
                FoodQuaterCard("대충 긴이름을 가진 햄버거",300,90, FoodTime.DINNER)
            }
        }
    }

}

@Composable
fun KcalCard(perAnime:Float) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp),shape =  RoundedCornerShape(50.dp)
    ) {
        Box(Modifier.fillMaxSize()){
            MainText(text = "칼로리", icon = Icons.Default.EnergySavingsLeaf,onclick = null)//mainText 구문
            Canvas(modifier = Modifier
                .padding(start = 20.dp, top = 80.dp)
                .size(200.dp)){
                DrawPieGraph(listOf(60*perAnime,20*perAnime,20*perAnime), listOf(BreakFast, Lunch, Dinner))
            }
            FoodScreenText(title = "아침",500, offset = 0.dp, color = BreakFast,"kcal")
            FoodScreenText(title = "점심",500, offset = 45.dp, color = Lunch,"kcal")
            FoodScreenText(title = "저녁",750, offset = 90.dp, color = Dinner,"kcal")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NatureCard(perAnime:Float) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp),shape =  RoundedCornerShape(50.dp)
    ) {
        val pagerstate = rememberPagerState()
        Box(Modifier.fillMaxSize()){
            Row(modifier = Modifier) {
                FoodTimeText(n = pagerstate.currentPage)
                Text(text = foodlist[pagerstate.currentPage].name, color = Color.Gray,modifier = Modifier
                    .padding(start = 15.dp, top = 20.dp), fontSize = 25.sp, fontWeight = FontWeight.Light)
            }//mainText 구문
            HorizontalPager(
                count = 3,
                state = pagerstate,
            ) { pager ->
                Box(Modifier.fillMaxSize()) {
                    
                    Canvas(modifier = Modifier
                        .padding(start = 10.dp, top = 80.dp, end = 30.dp)
                        .size(160.dp)){
                        DrawLineGraph(100-(60*perAnime).toInt(),20f,130f, Color.Red)
                        DrawLineGraph(100-(20*perAnime).toInt(),20f,250f, Color.Magenta)
                        DrawLineGraph(100-(40*perAnime).toInt(),20f,370f, Color.Cyan)
                    }
                    FoodScreenText(title = "당류", value =  foodlist[pager].sugar, offset = 0.dp, color = Color.Red,"g")
                    FoodScreenText(title = "지방", value =  foodlist[pager].fats, offset = 45.dp, color = Color.Magenta,"g")
                    FoodScreenText(title = "단백질", value =  foodlist[pager].protein, offset = 90.dp, color = Color.Cyan,"g")

                }
            }

            Canvas(modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(200.dp)){
                DrawSlide(280f,470f,3,pagerstate.currentPage)
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AdviceCard(navController: NavController) {
    //더미 정보입니다. 서버와 연결시 제거 요망
    val adviceFood = listOf<String>("커피","햄버거","소고기")
    val adviceNature = listOf<List<String>>(listOf("카페인","수분"), listOf("단백질","지방","탄수화물"), listOf("단백질"))

    //여기까지

    val segmentstate = remember {
        mutableStateOf(0)
    }
    val pagerstate = rememberPagerState()
    LaunchedEffect(segmentstate.value){
        pagerstate.animateScrollToPage(segmentstate.value)
    }
    SegmentButton(segmentstate, listOf("아침","점심","저녁"))
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(10.dp),shape =  RoundedCornerShape(35.dp)
    ) {
        HorizontalPager(count = 3,modifier = Modifier.fillMaxSize(), userScrollEnabled = false, state = pagerstate) {
            Box(modifier = Modifier.fillMaxSize()){
                val perAnime: Float by animateFloatAsState(if (it == pagerstate.currentPage) 1f else 0.000f, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
                //정적인 레이아웃
                MainText(text = adviceFood[it], icon = if (it==0)Icons.Default.FreeBreakfast else if(it==1)Icons.Default.LunchDining else Icons.Default.DinnerDining,onclick = null)//mainText 구문
                Text(text = "커피는 아침에 먹기 좋은 제일 좋은 음식입니다.커피는 아침에 먹기 좋은 제일 좋은 음식입니다.", Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 10.dp, y = 70.dp)
                    .padding(end = 60.dp))
                OutlinedButton(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp),onClick = {
                    navController.navigate("AddFood/${1}")
                }) {
                    Text(text = "추가하기", fontSize = 15.sp)
                }

                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 20.dp, y = (-20).dp)) {
                    Text(text = "영양소",Modifier.padding(5.dp),color = MaterialTheme.colorScheme.onSurfaceVariant)
                    adviceNature[it].forEachIndexed{i,nature ->
                        Card(Modifier.padding(horizontal = 2.dp)) {
                            Text(text = nature,Modifier.padding( 5.dp))
                        }
                    }
                }


            }
        }

    }
}