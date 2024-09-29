
package com.example.petstoreservice.PlashScreen.AddProfile
import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.common.NewsIconsButton
import com.example.petstoreservice.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddImgProFile (navController: NavHostController){
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Placeholder for the image, replace with your image resource or logic to load an image
        if (cameraPermissionState.status.isGranted) {
            CameraPreview(context)
        } else {
            Text(text = "Camera permission is required to use this feature.")
            Spacer(modifier = Modifier.height(16.dp))
//            IconButton(onClick = {
//                // Trigger permission request again
//                cameraPermissionState.launchPermissionRequest()
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Request Camera Permission"
//                )
//            }
        }


        Text(
            text = "Sử dụng camera để quét body thú cưng/ Quét mã QR code",
            fontSize = 12.sp,
        )

        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            NewsIconsButton(
                onClick = {},
                sizeBT = 100,
                sizeIm = 100,
                icon = R.drawable.ic_imphoto,
                colorButton = Color.White,
            )
            NewsIconsButton(
                onClick = {},
                sizeBT = 100,
                sizeIm = 100,
                icon = R.drawable.img_logo_cat,
                colorButton = Color("#4E9F6B".toColorInt()),
            )
            NewsIconsButton(
                onClick = {},
                sizeBT = 100,
                sizeIm = 100,
                icon = R.drawable.ic_flash,
                colorButton = Color.White,
            )
        }

    }
}


@Preview
@Composable
fun AddImgProFilePreview() {
    val navController = rememberNavController()
    AddImgProFile(navController)
}

