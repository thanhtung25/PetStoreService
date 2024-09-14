package com.example.petstoreservice.PlashScreen.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun NewsTextField(
    modifier: Modifier =Modifier,
    label : String?,
    placeholder : String?,
    text: String,
    onTextChange: (String) -> Unit,
    lendingIcon: ImageVector = Icons.Default.Person,
    isPassword: Boolean = false
){
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        leadingIcon = { Icon(lendingIcon, contentDescription = "")},
        label = { Text(text = label ?: "")},
        placeholder = { Text(text = placeholder ?: "")},
        shape = RoundedCornerShape(size = 10.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None // Sử dụng PasswordVisualTransformation để che mật khẩu
    )
}