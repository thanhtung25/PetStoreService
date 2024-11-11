package com.example.petstoreservice.PlashScreen.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTextField(
    modifier: Modifier =Modifier,
    label : String? = null,
    placeholder : String?,
    text: String,
    onTextChange: (String) -> Unit,
    lendingIcon: ImageVector? = null,
    isPassword: Boolean = false,
){
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp,20.dp, 0.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 10.dp)),
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        leadingIcon = lendingIcon?.let {
            { Icon(it, contentDescription = "") }
        },
        label = { Text(text = label ?: "")},
        placeholder = { Text(text = placeholder ?: "")},
        shape = RoundedCornerShape(size = 10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor =  Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
            unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = 1,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None, // Sử dụng PasswordVisualTransformation để che mật khẩu

    )
}