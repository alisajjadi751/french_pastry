package com.ali_sajjadi.frenchpastry.presentation.screen.login

import android.app.Application
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.presentation.Dimens.smallText

import com.ali_sajjadi.frenchpastry.viewModel.LoginViewModel
import com.ali_sajjadi.frenchpastry.ui.theme.TextColorLight

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    isSendCode: Boolean = false,
    errorMessage: String = "",
    viewModel: LoginViewModel = LoginViewModel(application = Application())
) {

    var showError by remember { mutableStateOf(false) }
    var isTimerError by remember { mutableStateOf(false) }
    val sendPhoneResponse by viewModel.sendCodeResponse.collectAsState()


    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
                showError = false
            },
            modifier = modifier
                .padding(top = 20.dp)
                .height(52.dp)
                .width(260.dp)
                .border(
                    1.dp,
                    color = if (!isError && !showError) Color(0xff555353) else Color.Red,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = smallText,
                textDirection = TextDirection.Content,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

            leadingIcon = {
                if (isSendCode) {
                    sendPhoneResponse?.let {
                        val timerValue = it.seconds
                        TimerBox(
                            totalTime = timerValue,
                            onTimeout = { isTimerError = true })
                    }
                }
            },
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    style = TextStyle(
                        fontSize = smallText,
                        color = TextColorLight,

                    )
                )
            },
            isError = isError
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))

        if (showError || isTimerError || isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = smallText,
                    color = Color.Red,
                    textDirection = TextDirection.Rtl,
                    textAlign = TextAlign.Right
                )
            )
        }
    }
}
