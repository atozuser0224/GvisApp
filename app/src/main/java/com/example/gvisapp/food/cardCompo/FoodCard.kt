@file:OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)

package com.example.gvisapp.food.cardCompo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberRichTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.R
import com.example.gvisapp.composable.util.DrawLineGraph
import com.example.gvisapp.composable.util.DrawPieGraph
import com.example.gvisapp.composable.util.DrawSlide
import com.example.gvisapp.composable.FoodScreenText
import com.example.gvisapp.composable.FoodTime
import com.example.gvisapp.composable.FoodTimeText
import com.example.gvisapp.composable.util.MainText
import com.example.gvisapp.composable.util.NextButtons
import com.example.gvisapp.composable.util.SegmentButton
import com.example.gvisapp.composable.util.underLine_TextField
import com.example.gvisapp.ui.theme.BreakFast
import com.example.gvisapp.ui.theme.Dinner
import com.example.gvisapp.ui.theme.Lunch
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun KcalCard() {
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
                DrawPieGraph(listOf(60f,20f,20f), listOf(BreakFast, Lunch, Dinner))
            }
            FoodScreenText(title = "아침",500, offset = 0.dp, color = BreakFast,"kcal")
            FoodScreenText(title = "점심",500, offset = 45.dp, color = Lunch,"kcal")
            FoodScreenText(title = "저녁",750, offset = 90.dp, color = Dinner,"kcal")
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NatureCard(navController: NavController,onClick:(()->Unit)?) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp),shape =  RoundedCornerShape(50.dp)
    ) {
        val pagerstate = rememberPagerState()
        Box(Modifier.fillMaxSize()){


            HorizontalPager(
                count = 3,
                state = pagerstate,
            ) { pager ->
                if (false){//test
                    Box(Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.fillMaxWidth()){
                            Row(Modifier.align(CenterStart)) {
                                FoodTimeText(n = pagerstate.currentPage)
                                Text(text = "Test", color = Color.Gray,modifier = Modifier
                                    .padding(start = 15.dp, top = 20.dp), fontSize = 25.sp, fontWeight = FontWeight.Light)

                            }//mainText 구문
                            onClick?.let{
                                TextButton(onClick = onClick,
                                    Modifier
                                        .align(CenterEnd)
                                        .padding(top = 10.dp, end = 10.dp)) {
                                    Text(text = "더보기",
                                        fontSize = 15.sp)
                                }
                            }
                        }

                        Canvas(modifier = Modifier
                            .padding(start = 10.dp, top = 80.dp, end = 30.dp)
                            .size(160.dp)){
                            DrawLineGraph(40,20f,130f, Color.Red)
                            DrawLineGraph(20,20f,250f, Color.Magenta)
                            DrawLineGraph(50,20f,370f, Color.Cyan)
                        }
                        FoodScreenText(title = "당류", value =  0, offset = 0.dp, color = Color.Red,"g")
                        FoodScreenText(title = "지방", value =  0, offset = 45.dp, color = Color.Magenta,"g")
                        FoodScreenText(title = "단백질", value =  0, offset = 90.dp, color = Color.Cyan,"g")

                    }
                }else{
                    Box(Modifier.fillMaxSize()) {
                        Column(Modifier.align(Center), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "${FoodTime.values()[pager].kor}을 안 드셨나요?", fontSize = 24.sp)
                            TextButton(onClick = {
                                navController.navigate("AddFood/${pager}")
                            }) {
                                Text(text = "음식 추가하기", fontSize = 18.sp)
                            }
                        }
                    }
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

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AdviceCard(navController: NavController,time:Int) {
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

                //정적인 레이아웃
                MainText(text = adviceFood[it], icon = if (it==0)Icons.Default.FreeBreakfast else if(it==1)Icons.Default.LunchDining else Icons.Default.DinnerDining,onclick = null)//mainText 구문
                Text(text = "커피는 아침에 먹기 좋은 제일 좋은 음식입니다.커피는 아침에 먹기 좋은 제일 좋은 음식입니다.", Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 10.dp, y = 70.dp)
                    .padding(end = 60.dp))
                OutlinedButton(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp),onClick = {
                    navController.navigate("AddFood/${if (time==0)it else time-1}")
                }) {
                    Text(text = "추가하기", fontSize = 15.sp)
                }

                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 20.dp, y = (-20).dp)) {
                    Text(text = "영양소",
                        Modifier
                            .padding(5.dp)
                            .align(CenterVertically),color = MaterialTheme.colorScheme.onSurfaceVariant)
                    adviceNature[it].forEachIndexed{i,nature ->

                        Card(Modifier.padding(horizontal = 2.dp), shape = RoundedCornerShape(25.dp)) {
                            val tooltipState = rememberRichTooltipState(isPersistent = true)
                            val scope = rememberCoroutineScope()
                            RichTooltipBox(
                                title = { Text(nature) },
                                action = {
                                    TextButton(
                                        onClick = { scope.launch { tooltipState.dismiss() } },Modifier.align(alignment = End)
                                    ) { Text("닫기") }
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                text = { Text("${nature}(은)는 몸에 좋음!") },
                                tooltipState = tooltipState
                            ) {
                                TextButton(onClick = { scope.launch { tooltipState.show() } }) {
                                    Text(
                                        text = nature
                                    )
                                }
                            }
                        }
                    }
                }


            }
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.AddNewFoodScreen_firstCard(text : MutableState<String>,pagerState: PagerState) {
    Column() {
        HorizontalDivider()
        MainText(text = stringResource(id = R.string.food), Icons.Default.RestaurantMenu,onclick = null)
        Text(text = "어떤 음식을 드셨는지 입력해주세요.",Modifier.padding(10.dp), fontSize = 24.sp)

    }
    underLine_TextField(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .align(Center)
            .offset(y = (-150).dp),
        label = {Text(text = "음식")},
        icon = null,
        shape = RoundedCornerShape(15.dp),
        onClick = {},
    ){
        text.value = it
    }
    NextButtons(num = 0, enable = text.value!="", nextClick = {
        CoroutineScope(Dispatchers.Default).launch{
            pagerState.scrollToPage(1)
        }
    }) {
    }
}
@Composable
fun BoxScope.AddNewFoodScreen_secondCard(text : MutableState<String>,pagerState: PagerState) {
    Column() {
        HorizontalDivider()
        MainText(text = stringResource(id = R.string.food), Icons.Default.RestaurantMenu,onclick = null)
        Text(text = "어떤 음식을 드셨는지 입력해주세요.",Modifier.padding(10.dp), fontSize = 24.sp)

    }
    underLine_TextField(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .align(Center)
            .offset(y = (-150).dp),
        label = {Text(text = "음식")},
        icon = null,
        shape = RoundedCornerShape(15.dp),
        onClick = {},
    ){
        text.value = it
    }
    NextButtons(num = 1, enable = text.value!="", nextClick = {
        CoroutineScope(Dispatchers.Default).launch{
            pagerState.scrollToPage(2)
        }
    }) {
        CoroutineScope(Dispatchers.Default).launch{
            pagerState.scrollToPage(0)
        }
    }

}