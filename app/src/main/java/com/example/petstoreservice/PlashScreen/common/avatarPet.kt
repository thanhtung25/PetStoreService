package com.example.petstoreservice.PlashScreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.petstoreservice.R

@Composable
fun avatarPet() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(160.dp) // Increase the size of the container if needed
    ) {
        // Main Image (Cat image)
        Image(
            modifier = Modifier
                .size(120.dp) // Main image size
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape), // Adjust border size as needed
            painter = painterResource(id = R.drawable.img_avt),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        // QR Code overlay (positioned at bottom center of main image)
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White)
                .align(Alignment.BottomCenter), // Align at bottom center of main image
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(35.dp) // Image size inside the border
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_qr_code),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}