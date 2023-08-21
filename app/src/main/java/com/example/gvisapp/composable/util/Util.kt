package com.example.gvisapp.composable.util

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.R
import com.example.gvisapp.ui.theme.Purple40
import com.example.gvisapp.ui.theme.Purple80
import com.example.gvisapp.ui.theme.PurpleGrey40
import com.example.gvisapp.ui.theme.PurpleGrey80

fun DrawScope.DrawLineGraph(percent:Int, offsetX: Float, offsetY:Float, color: Color){

    drawLine(start = Offset(30f+offsetX,offsetY), end = Offset(size.width-30,offsetY), color = Color.White,strokeWidth = 40F, cap = StrokeCap.Round)
    drawLine(start = Offset(30f+offsetX,offsetY), end = Offset(size.width-30-(((size.width-30-offsetX)/100)*percent),offsetY), color = color,strokeWidth = 30f, cap = StrokeCap.Round)

}

fun DrawScope.DrawPieGraph(percentList:List<Float>,colorList: List<Color>){
    drawArc(
        color = Color.LightGray,
        startAngle = 120f,
        sweepAngle = 180f,
        useCenter = false,
        size = Size(500.dp.value, 500.dp.value),
        style = Stroke(width = 40f, cap = StrokeCap.Round)
    )
    var offset = 0f
    percentList.forEachIndexed { index, i ->
        val n: Float = (i/100f)*180f
        drawArc(
            color = colorList[index],
            startAngle = 120+offset,
            sweepAngle = n,
            useCenter = false,
            size = Size(500.dp.value, 500.dp.value),
            style = Stroke(width = 40f, cap = StrokeCap.Round)
        )
        offset+=n
    }

}
fun DrawScope.DrawSlide(offsetX: Float,offsetY: Float,repeat:Int,ans:Int){
    var n =offsetX
    repeat(repeat){

        drawLine(start = Offset(n,offsetY), end = Offset(if (it == ans) n+20f else n,offsetY), color = if(it == ans) PurpleGrey80 else Color.White,strokeWidth = 30F, cap = StrokeCap.Round)
        n+=if (it == ans) 70f else 50f
    }
}
@Composable
fun BottomNavBar(n: MutableState<Int>,navController: NavController){
    NavigationBar(
        modifier = Modifier.height(80.dp),

        ) {
        NavBarItem(name = stringResource(id = R.string.Home), Icons.Rounded.Home){
            n.value=0
            navController.navigate("SCREEN"){
                popUpTo("SCREEN") { inclusive = true }
            }
        }
        NavBarItem(name = "Profile", icon =  Icons.Rounded.Person){
            n.value=1
        }
        NavBarItem(name = "Menu", Icons.Rounded.Menu){
            n.value=2
        }
    }
}
@Composable
fun RowScope.NavBarItem(name : String, icon: ImageVector,onclick: () -> Unit){
    NavigationBarItem(selected = false, icon = { Icon(
        imageVector = icon,
        contentDescription = name,
        Modifier
            .width(35.dp)
            .height(35.dp)
            .offset(y = (-5).dp)
    )
    }, onClick = onclick,
        label = { Text(name, fontSize = 12.sp,modifier = Modifier.offset(y= (-5).dp)
        ) },
        modifier = Modifier
            .fillMaxWidth()
            .width(50.dp)
            .offset(y = 5.dp)
    )
}
@Composable
fun MainText(text:String, icon: ImageVector, onclick:(()->Unit)?){
    Row() {
        Icon(imageVector = icon,modifier = Modifier
            .size(35.dp)
            .offset(x = 15.dp, y = 17.dp), contentDescription = "IconsMainText")
        Text(text = text,Modifier.padding(start = 15.dp, top = 15.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        if (onclick != null){
            IconButton(onClick = onclick,modifier = Modifier
                .padding(start = 15.dp, top = 19.dp)
                .size(35.dp)) {
                Icon(imageVector = Icons.Default.ArrowForwardIos,modifier = Modifier
                    .size(32.dp), contentDescription = "arrowForward")
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.QuaterCard(num:Int, onclick: () -> Unit, content:@Composable ColumnScope.()->Unit){
    ElevatedCard(shape = RoundedCornerShape(35.dp),
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f)
            .align(
                alignment = when (num) {
                    1 -> Alignment.TopStart
                    2 -> Alignment.TopEnd
                    3 -> Alignment.BottomStart
                    else -> Alignment.BottomEnd
                }
            )
            .padding(vertical = 2.dp, horizontal = 2.dp),
        content = content,
        onClick = onclick
    )
}
@Composable
fun SegmentButton(state: MutableState<Int>,list: List<String>){
    Card(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(25.dp)) {
        Row(Modifier.fillMaxSize()) {
            list.forEachIndexed { index, s ->
                Button(onClick = {state.value = index},
                    Modifier
                        .width((390 / list.size).dp)
                        .fillMaxHeight()
                        .padding(3.dp), shape = RoundedCornerShape(25.dp), colors = if (state.value == index) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary) else ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant, contentColor = MaterialTheme.colorScheme.onSurfaceVariant)) {
                    Text(text = s, fontSize = 20.sp)
                }
            }
        }
    }
}
fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}