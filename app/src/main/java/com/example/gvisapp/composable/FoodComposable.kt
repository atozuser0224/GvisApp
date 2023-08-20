package com.example.gvisapp.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxScope.FoodCardText(text:String,modifier: Modifier? = Modifier){
    Text(text = text,//칼로리 텍스트
        modifier!!
            .align(Alignment.CenterStart)
            .padding(start = 5.dp)
            , fontSize = 15.sp, fontWeight = FontWeight.W100)

}
@Composable//영양소 구문에서 칼로리 지방 단백질을 표시함
fun BoxScope.FoodScreenText(title:String,value:Int,offset: Dp,color: Color?,suffix:String){
    Text("$title : $value $suffix",modifier =  Modifier
        .align(Alignment.TopEnd)
        .offset(x = (-120).dp, y = 120.dp + offset)
        .padding(start = ((value.toString().length*20).dp))
   )

}
@Composable//영양소 구문에서 칼로리 지방 단백질을 표시함
fun ColumnScope.FoodScreenText(title:String,value:Int,offset: Dp,color: Color,suffix:String){
    Row(
        Modifier
            .offset(x = (-120).dp, y = 120.dp + offset)) {
        Text(text = "$title : ",
            textAlign = TextAlign.Start, color = color)
        Text(text = "${value.toString()}${suffix}",
            textAlign = TextAlign.Start)

    }

}
@Composable//영양소 구문에서 칼로리 지방 단백질을 표시함
fun BoxScope.FoodScreenText(title:String,value:Float,offset: Dp,color: Color,suffix:String){
    Row(
        Modifier
            .align(Alignment.TopEnd)
            .offset(x = (-120).dp, y = 120.dp + offset)) {
        Text(text = "$title : ",
            textAlign = TextAlign.Start, color = color)
        Text(text = "${value.toString()}${suffix}",
            textAlign = TextAlign.Start)

    }

}


@Composable
fun TextButtonNavBar(title:String, icon: ImageVector, color: Color, onclick:()->Unit){
    TextButton(onClick = onclick) {
        Icon(imageVector = icon, contentDescription = "textButtonIcon", tint = color)
        Text(text = title,Modifier.padding(start = 5.dp, top = 10.dp, bottom = 10.dp), fontSize = 20.sp, color = color)
    }
}
@Composable
fun NavRow(select: MutableState<Int>){
    LazyRow(
        Modifier
            .fillMaxWidth()
            .height(60.dp)) {
        items(4){
            TextButtonNavBar(title = when(it){
                0 -> "오늘의 식단"
                1 -> "아침"
                2 -> "점심"
                else -> "저녁"
            }, icon = when(it){
                0 -> Icons.Default.MenuBook
                1 -> Icons.Default.FreeBreakfast
                2 -> Icons.Default.LunchDining
                else -> Icons.Default.DinnerDining
            },color = if (select.value == it) MaterialTheme.colorScheme.primary else Color.Gray) {
                select.value = it
            }
        }
    }
}
@Composable
fun NavRow(select: MutableState<Int>,list : List<String>,iconList : List<ImageVector>){
    LazyRow(
        Modifier
            .fillMaxWidth()
            .height(60.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        items(list.size){
            TextButtonNavBar(title = list[it], icon = iconList[it],color = if (select.value == it) MaterialTheme.colorScheme.primary else Color.Gray) {
                select.value = it
            }
        }
    }
}
@Composable
fun FoodTimeText(n:Int) {
    MainText(
        text = when (n) {
            0 -> "아침"
            1 -> "점심"
            2 -> "저녁"
            else -> ""
        }, icon = when (n) {
            0 -> Icons.Default.FreeBreakfast
            1 -> Icons.Default.LunchDining
            2 -> Icons.Default.DinnerDining
            else -> Icons.Default.DinnerDining
        }, onclick = null
    )
}
enum class FoodTime(){
    BREAKFAST,
    LUNCH,
    DINNER
}
