package com.example.gvisapp.Food

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gvisapp.composable.DrawLineGraph
import com.example.gvisapp.composable.FoodCardText
import com.example.gvisapp.composable.FoodTime

@Composable
fun FoodQuaterCard(title:String,kcal:Int,percent:Int,time: FoodTime){
    Box(Modifier.fillMaxSize()){
        Row(Modifier.align(Alignment.TopStart).padding(start = 5.dp,top=5.dp)) {//아이콘 + 텍스트로 이루어진 ROW 음식을 먹은 시간대 의미
            Icon(//아이콘
                imageVector = when (time) {
                    FoodTime.BREAKFAST -> Icons.Default.FreeBreakfast
                    FoodTime.LUNCH -> Icons.Default.LunchDining
                    else -> Icons.Default.DinnerDining
                }, contentDescription = "Icons", Modifier
                    .padding(top = 10.dp , start = 10.dp)
            )//텍스트
            Text(text = when(time){
                FoodTime.BREAKFAST -> "아침"
                FoodTime.LUNCH -> "점심"
                else -> "저녁"
            },
                Modifier
                    .padding(top = 7.dp, start = 5.dp), fontSize = 22.sp)
        }
        var start by remember {
            mutableStateOf(false)
        }
        val perAnime: Int by animateIntAsState(if (start) percent else 100, animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
        )
        LaunchedEffect(Unit){
            start = true
        }
        Text(text = title,//음식 이름 텍스트
            Modifier
                .align(Alignment.TopStart)
                .padding(start = 11.dp, top = 35.dp), fontSize = 15.sp, fontWeight = FontWeight.W100, color = Color.Gray)

        FoodCardText(text = "$kcal 칼로리")
        FoodCardText(text = "100 지방", Modifier.padding(top = 55.dp))
        FoodCardText(text = "$kcal 당류", Modifier.padding(top=110.dp))
        //차트 그래프 캔버스
        Canvas(modifier = Modifier
            .align(Alignment.CenterEnd)
            .fillMaxWidth()
            .fillMaxHeight(0.3f)){
            DrawLineGraph(perAnime,210f,75f, Color.Red)
            DrawLineGraph(perAnime,210f,150f, Color.Magenta)
            DrawLineGraph(perAnime,210f,225f, Color.Cyan)
        }

    }
}