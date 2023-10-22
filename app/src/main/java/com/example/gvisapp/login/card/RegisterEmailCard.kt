package com.example.gvisapp.login.card

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.units.Mass
import androidx.navigation.NavController
import com.example.gvisapp.composable.NavRow
import com.example.gvisapp.composable.util.round_TextField
import com.example.gvisapp.data.HealthConnectManager
import com.example.gvisapp.data.MailRequest
import com.example.gvisapp.data.SingleResult
import com.example.gvisapp.data.UserSignInRequest
import com.example.gvisapp.data.apiService
import com.example.gvisapp.suite_font
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun RegisterEmailCard(pagerState: PagerState,navController: NavController,snackbarHostState: SnackbarHostState,healthConnectManager: HealthConnectManager){
    Box(Modifier.fillMaxSize()) {//메인 로그인화면
        val coroutine = rememberCoroutineScope()
        val lazyscroll = rememberLazyListState()
        BackHandler {
            coroutine.launch {
                pagerState.animateScrollToPage(1)
            }
        }
        val email = remember {
            mutableStateOf("")
        }
        val focusRequester by remember { mutableStateOf(FocusRequester()) }

        LaunchedEffect(true){
            focusRequester.requestFocus()
        }

        LazyColumn(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize(), state = lazyscroll){
            item { 
                Spacer(modifier = Modifier.height(35.dp))
            }
            items(10) {
                round_TextField(
                    text = email,
                    modifier = Modifier
                        .animateItemPlacement(
                            spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        )
                        .fillMaxWidth()
                        .animateContentSize()
                        .height(if (lazyscroll.firstVisibleItemIndex == it) 140.dp else 100.dp)
                        .padding(5.dp),
                    label = lazyscroll.firstVisibleItemScrollOffset.toString(),
                    icon = Icons.Rounded.Email,
                    shape = RoundedCornerShape(25.dp),
                    onChange = {
                        email.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    focusRequester = focusRequester,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            coroutine.launch {
                                lazyscroll.animateScrollToItem(it+2)
                            }
                        }
                    )
                )
            }
        }
    }
}