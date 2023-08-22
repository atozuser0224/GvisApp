package com.example.gvisapp.food

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.R
import com.example.gvisapp.composable.util.BottomNavBar
import com.example.gvisapp.composable.util.MainText
import com.example.gvisapp.composable.util.def_TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewFoodScreen(bottomValue: MutableState<Int>, where: Int?, navController: NavController){
    val text = remember {
        mutableStateOf("")
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
            .fillMaxSize()) {
            Column() {
                HorizontalDivider()
                MainText(text = stringResource(id = R.string.food), Icons.Default.RestaurantMenu){
                    navController.navigate("Food/${0}")
                }
                Text(text = "어떤 음식을 드셨는지 입력해주세요.",Modifier.padding(10.dp), fontSize = 24.sp)

            }
            def_TextField(
                text = text,
                modifier = Modifier.fillMaxWidth().height(75.dp).align(Alignment.Center).offset(y = (-150).dp).padding(5.dp),
                label = "",
                icon = Icons.Default.Cancel,
                shape = RoundedCornerShape(25.dp),
                onClick = { /*TODO*/ },
            ){
                text.value = it
            }
        }

    }
}
