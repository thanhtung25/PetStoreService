package com.example.petstoreservice.PlashScreen.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

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