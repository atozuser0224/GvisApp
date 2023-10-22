package com.example.gvisapp.login.card

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.composable.util.codeTextField
import com.example.gvisapp.composable.util.codeTextField_Cards
import com.example.gvisapp.data.CommonResult
import com.example.gvisapp.data.IsMailEqualCode
import com.example.gvisapp.data.IsMailRequest
import com.example.gvisapp.data.apiService
import com.example.gvisapp.suite_font
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginGetMailCard(mail:MutableState<String>,pagerState: PagerState,navController: NavController, snackbarHostState: SnackbarHostState){
    val coroutine = rememberCoroutineScope()
    val code = remember {
        mutableStateOf("")
    }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    BackHandler {
        coroutine.launch {
            pagerState.animateScrollToPage(1)
        }
    }

    Box(Modifier.fillMaxSize()) {//메인 로그인화면
        Text(text = "인증코드가 발송되었습니다.",modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 5.dp), fontFamily = suite_font, fontSize = 18.sp)
        codeTextField(focusRequester = focusRequester, text = code, num = 4)
        codeTextField_Cards(
            text = code,
            num = 4,
            focusRequester = focusRequester,
            Rowmodifier = Modifier
                .align(Alignment.Center)
                .height(90.dp)
                .fillMaxWidth()
                .offset(y = (-175).dp),
            cardModifier = Modifier
                .fillMaxHeight()
                .width(90.dp)
                .padding(2.dp),
            shape =RoundedCornerShape(45.dp)
        )
        Button(onClick = {
            Log.d("code",code.value)
            Log.d("mail",mail.value)
            var call = apiService.isMail(IsMailRequest(code.value,mail.value))
            call.enqueue(object : Callback<CommonResult> {
                override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                    if (response.body()!!.success){
                        navController.navigate("SCREEN"){
                            popUpTo("LOGIN"){
                                inclusive = true
                            }
                        }
                    }else{
                        coroutine.launch {
                            snackbarHostState.showSnackbar(response.body()!!.msg)
                        }
                    }
                }

                override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                    Log.d("error",call.toString())
                }

            })
        },modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(10.dp)
            .width(240.dp)
            .height(65.dp)
            .offset(y = (-100).dp), shape = RoundedCornerShape(30.dp)
        ) {
            Text(text = "로그인", fontSize = 24.sp, fontFamily = suite_font, fontWeight = FontWeight.Light)
        }
    }
}