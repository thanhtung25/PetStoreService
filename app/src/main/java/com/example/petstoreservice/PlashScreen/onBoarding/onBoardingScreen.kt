package com.example.petstoreservice.PlashScreen.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.petstoreservice.PlashScreen.Dimens.PageIndicatorWidth
import com.example.petstoreservice.PlashScreen.Dimens.mediumPadding2
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import com.example.petstoreservice.PlashScreen.onBoarding.components.PageIndicator
import com.example.petstoreservice.PlashScreen.onBoarding.components.TextBoardingPage
import com.example.petstoreservice.PlashScreen.onBoarding.components.onBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoardingScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp, 30.dp),
//        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0,1,2,3-> listOf("Tiếp Tục")
                    4-> listOf("Bắt Đầu")
                    else -> listOf("")
                }
            }
        }
        TextButton(
            modifier = Modifier.align(Alignment.End).padding(10.dp),
            onClick = {navController.navigate(NavigationIteam.login.route)}
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Bỏ qua",
                    color = Color.Gray
                )
                Icon(
                    imageVector =  Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray
                )

            }
        }

        HorizontalPager(state = pagerState) {
            index -> onBoardingPage(page = pages[index])
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier
                    .width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
                selectedColor = Color("#509f6b".toColorInt()),
                unselectedColor = Color("#c1e5bb".toColorInt())
                )
        }
//        HorizontalPager(state = pagerState) {
//                index -> TextBoardingPage(page = pages[index])
//        }

        val scope = rememberCoroutineScope()
        if(buttonState.value[0].isNotEmpty()){
            NewsTextButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = mediumPadding2),
                text = buttonState.value[0],
                onClick = {
                    scope.launch {
                        if(pagerState.currentPage ==4){
                            navController.navigate(NavigationIteam.login.route)
                        }else{
                            pagerState.animateScrollToPage(page = pagerState.currentPage +1)
                        }
                    }
                }
            )
        }
    }
}