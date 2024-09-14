package com.example.petstoreservice.PlashScreen.onBoarding.components
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petstoreservice.PlashScreen.Dimens.PageIndicatorWidth
import com.example.petstoreservice.PlashScreen.Dimens.mediumPadding1
import com.example.petstoreservice.PlashScreen.Dimens.mediumPadding2
import com.example.petstoreservice.PlashScreen.onBoarding.Page
import com.example.petstoreservice.PlashScreen.onBoarding.pages
import com.example.petstoreservice.ui.theme.PetStoreServiceTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(
        modifier = modifier,
    ) {
        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {}
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
        Image(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.6f)
                .fillMaxWidth(),
            painter = painterResource(id = page.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = page.title,
            modifier =Modifier.fillMaxWidth().padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(
            text = page.description,
            modifier =Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun TextBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true)
@Composable
fun OnBoaringPagePreview(){
    PetStoreServiceTheme{
        onBoardingPage(page = pages[0])
    }
}