package com.example.petstoreservice.PlashScreen.common

import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.R

@Composable
fun NewsTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick:() ->Unit,
    colorButton: Color = Color("#4e9f6b".toColorInt()),
){
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButton,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
        ) {
        Text(
            text = text,
            fontSize = 24.sp
        )
    }

}

@Composable
fun NewsIconsButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon : Int = R.drawable.ic_edit,
    onClick:() ->Unit,
    colorButton: Color = Color.Gray,
    sizeBT: Int = 120,
    sizeIm: Int = 34,
    sizeBoder: Int = 6,
){
    Button(
        modifier = modifier.size(sizeBT.dp),
        onClick = onClick,
        colors =  ButtonDefaults.buttonColors(
            containerColor = colorButton,
            contentColor = colorButton,
        ),
        shape = RoundedCornerShape(size = sizeBoder.dp)
    ) {
        Image(
            modifier = Modifier.size(sizeIm.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }


}