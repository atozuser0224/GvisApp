package com.example.gvisapp.food

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gvisapp.composable.JamoUtils
import com.example.gvisapp.composable.util.BottomNavBar
import com.example.gvisapp.composable.setTimeDropdownMenuBox
import com.example.gvisapp.composable.util.MainText
import com.example.gvisapp.composable.addFoodListItemCard
import com.example.gvisapp.composable.isEqualKorean
import com.example.gvisapp.composable.util.addFocusCleaner
import com.example.gvisapp.composable.util.def_TextField
import com.example.gvisapp.data.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(bottomValue: MutableState<Int>, where: Int?, navController: NavHostController) {
    val selectTime = remember {
        mutableStateOf(when(where){
            0->"아침"
            1->"점심"
            2->"저녁"
            else ->"간식"
        })
    }
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ADD FOOD")
                },
                actions = {
                    setTimeDropdownMenuBox(arrayOf("아침","점심","저녁","간식"),selectTime)
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
                .fillMaxSize()
                .addFocusCleaner(focusManager)
        ) {
            //더미데이터 생성
            val foodList:ArrayList<Food> = ArrayList<Food>()
            val scoreList = remember {
                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0)
            }
            //변수를 저장하는곳
            val search = remember {
                mutableStateOf("")
            }
            val whoClick = remember {
                mutableStateOf(21000000)
            }

            //ui를 구현하는곳
            HorizontalDivider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    Modifier
                        .align(TopStart)
                        .offset(y = 5.dp)) {
                    MainText(text = "드신 음식이 있나요?", icon = Icons.Default.FoodBank, onclick = null)
                    Text(
                        text = "아래 리스트에서 선택해주세요!",
                        Modifier
                            .padding(bottom = 20.dp, start = 20.dp),
                        fontSize = 23.sp,
                        color = Color.Gray
                    )
                    def_TextField(text = search, modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                        .height(75.dp), label = "검색하기", icon = Icons.Default.Cancel, shape = RoundedCornerShape(15.dp), onClick = {
                            search.value =""
                            focusManager.clearFocus()
                    }) {

                        search.value = it
                        foodList.forEachIndexed { index, food ->
                            scoreList[index] = search.value.isEqualKorean(food.name)
                        }



                    }
                    LazyColumn(
                        Modifier
                            .padding()
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(bottom = 10.dp)

                    ) {
                        if (search.value.isEmpty()){
                            itemsIndexed(foodList) { index, food ->//Card list item
                                addFoodListItemCard(text = food.name,color = if (whoClick.value == index )MaterialTheme.colorScheme.primary else Color.Transparent){
                                    if  (whoClick.value == index){
                                        whoClick.value =21000000
                                    }else{
                                        whoClick.value = index
                                    }
                                }
                            }
                        }else {
                            itemsIndexed(foodList.mapIndexed{ index, food ->  index to food}.filter { scoreList[it.first] > 0 }.sortedWith(compareBy { scoreList[it.first] }).reversed()) { index, item ->//Card list item
                                addFoodListItemCard(text = item.second.name,color = if (whoClick.value == item.first )MaterialTheme.colorScheme.primary else Color.Transparent){
                                    if  (whoClick.value == item.first){
                                        whoClick.value =21000000
                                    }else{
                                        whoClick.value = item.first

                                    }
                                }
                            }
                        }

                    }
                }
                Text(text = if (whoClick.value == 21000000)"보기에 없으신가요?" else "",modifier = Modifier
                    .align(BottomEnd)
                    .offset(x = (-15).dp, y = -(60).dp), fontWeight = FontWeight.ExtraLight, fontSize = 16.sp,color = Color.Gray
                )
                Button(
                    onClick = {
                        if (whoClick.value == 21000000){
                            navController.navigate("AddNewFood/${where}")
                        }else{
                            navController.popBackStack()
                        }
                    }, modifier = Modifier
                        .align(BottomEnd)
                        .padding(10.dp)
                        .width(150.dp)
                        .height(50.dp)
                ) {
                    Text(text = if (whoClick.value == 21000000)"새로 추가" else "다음으로")
                }
            }
        }
    }
}





