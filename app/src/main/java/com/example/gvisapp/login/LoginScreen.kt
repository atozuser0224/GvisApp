package com.example.gvisapp.login

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gvisapp.data.HealthConnectManager
import com.example.gvisapp.login.card.LoginFirstCard
import com.example.gvisapp.login.card.LoginGetMailCard
import com.example.gvisapp.login.card.RegisterEmailCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun LoginScreen(navController: NavController,healthConnectManager: HealthConnectManager) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val snackbarHostState = remember {
            SnackbarHostState()
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) {it->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val pagerstate = rememberPagerState()
                val mail = remember {
                    mutableStateOf("")
                }

                var boxMove_bool by remember {
                    mutableStateOf(false)
                }
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
                }
                LaunchedEffect(pagerstate.currentPage){
                    when(pagerstate.currentPage){
                        0 -> boxMove_bool= false
                        1 -> boxMove_bool= false
                        2 -> boxMove_bool= false
                    }
                    delay(350)
                }
                ElevatedCard(
                    modifier = Modifier
                        .animateContentSize()
                        .fillMaxWidth(0.96f)
                        .fillMaxHeight(if (boxMove_bool) 0.9f else 0.715f)
                        .align(Alignment.BottomCenter)
                        ,
                    shape = RoundedCornerShape(50.dp,50.dp,0.dp,0.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

                ) {
                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        count = 4,
                        state = pagerstate,
                        userScrollEnabled = false
                    ) {

                        when(it){
                            0 -> LoginGetMailCard(mail = mail, pagerState = pagerstate, navController = navController,snackbarHostState)
                            1-> LoginFirstCard(pagerstate,navController,mail,snackbarHostState)
                            2-> RegisterEmailCard(pagerstate,navController,snackbarHostState,healthConnectManager)
                        }
                    }
                }
            }
        }

    }
}