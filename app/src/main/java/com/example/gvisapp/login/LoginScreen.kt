package com.example.gvisapp.login

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gvisapp.MainScreen
import com.example.gvisapp.composable.NavRow
import com.example.gvisapp.composable.util.getDark
import com.example.gvisapp.composable.util.round_TextField
import com.example.gvisapp.data.MailRequest
import com.example.gvisapp.data.Result
import com.example.gvisapp.data.SingleResult
import com.example.gvisapp.data.UserData
import com.example.gvisapp.data.apiService
import com.example.gvisapp.food.AddFoodScreen
import com.example.gvisapp.food.AddNewFoodScreen
import com.example.gvisapp.food.FoodScreen
import com.example.gvisapp.food.cardCompo.AdviceCard
import com.example.gvisapp.food.cardCompo.KcalCard
import com.example.gvisapp.food.cardCompo.NatureCard
import com.example.gvisapp.suite_font
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val pagerstate = rememberPagerState()
            LaunchedEffect(true){
                pagerstate.scrollToPage(1)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(
                    MaterialTheme.colorScheme.inversePrimary,
                    RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp)
                )){
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "Welcome",modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 36.sp, fontFamily = suite_font, fontWeight = FontWeight.ExtraBold)
                    Text(text = "로그인을 진행해주세요.",modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 18.sp, fontFamily = suite_font, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface.getDark())

                }
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .fillMaxHeight(0.67f)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-50).dp),
                shape = RoundedCornerShape(50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

            ) {
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    count = 4,
                    state = pagerstate,
                    userScrollEnabled = false
                ) {
                    when(pagerstate.currentPage){
                        1-> LoginFirstCard(pagerstate,navController)
                    }
                }
            }
        }
    }
}