package com.example.gvisapp.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gvisapp.composable.NavRow
import com.example.gvisapp.composable.util.round_TextField
import com.example.gvisapp.data.MailRequest
import com.example.gvisapp.data.SingleResult
import com.example.gvisapp.data.UserSignInRequest
import com.example.gvisapp.data.apiService
import com.example.gvisapp.suite_font
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@OptIn(ExperimentalPagerApi::class)
@Composable
fun LoginFirstCard(pagerState: PagerState,navController: NavController){
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val annotated = remember {
        mutableStateOf("")
    }
    val isPasswordVisible = remember {
        mutableStateOf(false)
    }
    val click = remember {
        mutableStateOf(0)
    }
    val coroutine = rememberCoroutineScope()
    Box(Modifier.fillMaxSize()) {//메인 로그인화면
        NavRow(select = click, listOf("이메일만","비밀번호 사용"), listOf(Icons.Rounded.Email, Icons.Rounded.Password))
        round_TextField(
            text = email,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(130.dp)
                .padding(top = 75.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.TopCenter),
            label = "이메일",
            icon = Icons.Rounded.Email,
            shape = RoundedCornerShape(20.dp),
            onChange = {
                email.value = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        if(click.value==1){
            round_TextField(
                text = password,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(205.dp)
                    .padding(top = 150.dp, start = 20.dp, end = 20.dp)
                    .align(Alignment.TopCenter),
                label = "비밀번호",
                icon =if (isPasswordVisible.value) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                shape = RoundedCornerShape(20.dp),
                onChange = {
                    password.value = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (!isPasswordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
                onClick = {
                    isPasswordVisible.value = !isPasswordVisible.value
                }
            )
        }
        Text(text = annotated.value,modifier = Modifier
            .padding( start = 20.dp, end = 20.dp)
            .align(Alignment.TopStart)
            .offset(y=if (click.value ==1)215.dp else 165.dp))


        Button(
            onClick = {
                if (click.value==1){
                    var call = apiService.SignIn(UserSignInRequest(email.value,password.value))

                    call.enqueue(object : Callback<SingleResult<String?>> {
                        override fun onResponse(
                            call: Call<SingleResult<String?>>,
                            response: Response<SingleResult<String?>>
                        ) {
                            if (response.body() != null && response.body()?.success==true){
                                navController.navigate("SCREEN"){
                                    popUpTo("LOGIN"){
                                        inclusive = true
                                    }
                                }
                            }else{
                                annotated.value = "로그인이 실패하였습니다."
                            }

                        }

                        override fun onFailure(call: Call<SingleResult<String?>>, t: Throwable) {
                            annotated.value = "로그인이 실패하였습니다."
                        }

                    })
                }else{
                    var call = apiService.getMail(MailRequest(email.value))

                    call.enqueue(object : Callback<String> {
                        override fun onResponse(
                            call: Call<String>,
                            response: Response<String>
                        ) {
                            Log.d("test",response.body()!!)
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.d("test",t.message?:"")
                        }

                    })
                    coroutine.launch {
                        pagerState.animateScrollToPage(0)
                    }

                }




            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
                .width(240.dp)
                .height(65.dp)
                .offset(y = (-100).dp), shape = RoundedCornerShape(30.dp)
        ) {
            Text(text = "로그인", fontSize = 32.sp, fontFamily = suite_font, fontWeight = FontWeight.Light)
        }
    }
}