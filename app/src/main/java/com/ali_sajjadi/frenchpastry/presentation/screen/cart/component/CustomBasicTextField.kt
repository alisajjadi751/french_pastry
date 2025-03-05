package com.ali_sajjadi.frenchpastry.presentation.screen.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    textColor: Color = Color.Black,
    placeholderColor: Color = Color.Gray,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray,
    cornerRadius: Dp = 10.dp
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(0.dp), // حذف پدینگ داخلی
        singleLine = true,
        textStyle = TextStyle(
            color = textColor,
            fontSize = 14.sp
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            color = placeholderColor,
                            fontSize = 14.sp
                        )
                    )
                }
                innerTextField() // نمایش محتوای وارد شده توسط کاربر
            }
        }
    )
}