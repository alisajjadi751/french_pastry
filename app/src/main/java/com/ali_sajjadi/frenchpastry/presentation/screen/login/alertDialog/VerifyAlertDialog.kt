 package com.ali_sajjadi.frenchpastry.presentation.screen.login.alertDialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.Dimens.mediumText
import com.ali_sajjadi.frenchpastry.presentation.Dimens.roundedCornerButton
import com.ali_sajjadi.frenchpastry.presentation.Dimens.smallText
import com.ali_sajjadi.frenchpastry.presentation.screen.login.CustomTextField
import com.ali_sajjadi.frenchpastry.viewModel.LoginViewModel
import com.ali_sajjadi.frenchpastry.ui.theme.TextColorLight


@Composable
fun AlertVerify(
    onDismiss: () -> Unit
) {

    val viewModel: LoginViewModel = viewModel()
    val phone by viewModel.phone.collectAsState()
    var code by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isTimerActive by remember { mutableStateOf(true) }

    val verifyCodeResponse by viewModel.verifyCode.collectAsState(initial = null)

    LaunchedEffect(verifyCodeResponse) {
        verifyCodeResponse?.let { response ->
            if (response.success == 0) {
                isError = true
                errorMessage = "کد وارد شده اشتباه است"
            } else {
                isError = false
                errorMessage = ""
                // می‌توانید کارهای موفقیت‌آمیز دیگر را اینجا انجام دهید
            }
        }
    }
    AlertDialog(
        modifier = Modifier
            .width(300.dp)
            .background(Color.Transparent),
        onDismissRequest = {
        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(260.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {
                        if (code.isEmpty()) {
                            isError = true
                            errorMessage = "لطفاً کد تایید را وارد کنید"
                        } else {
                            viewModel.verifyCode2(code = code, phone = phone)
                        }
                        if (verifyCodeResponse?.success == 0){
                            isError = true
                            errorMessage = "کد وارد شده اشتباه است"
                        }
                    },
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(roundedCornerButton),

                    ) {
                    Text(
                        text = "تائید و ادامه",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = smallText
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ارسال مجدد کد",
                        color = if (isError) Color.Gray else Color.Black,
                        style = TextStyle(
                            fontSize = smallText,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .clickable(enabled = !isTimerActive) {
                                viewModel.sendPhone2(phone)
                                isTimerActive = true
                            }
                    )
                    Text(
                        text = "ویرایش شماره",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = smallText,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .clickable {
                                onDismiss()
                            }
                    )
                }
            }
        },
        icon = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painterResource(R.drawable.close),
                    contentDescription = null,
                    Modifier
                        .size(25.dp)
                        .clickable {
                          onDismiss()
                        }
                )
                Spacer(Modifier.padding(top = 8.dp))
                Text(
                    text = "کد تائید",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = mediumText,
                        fontWeight = FontWeight.Bold,
                        textDirection = TextDirection.Rtl
                    )
                )

                Spacer(Modifier.padding(top = 8.dp))

                Text(
                    text = "کد ارسالی به شماره $phone را وارد کنید",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        color = TextColorLight,
                        fontSize = smallText,
                        textDirection = TextDirection.Rtl
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))

                CustomTextField(
                    value = code,
                    onValueChange = { newValue ->
                        code = newValue
                    },
                    placeholder = "کد تایید را وارد کنید",
                    viewModel = viewModel,
                    isSendCode = true,
                    errorMessage = errorMessage,
                    isError = isError
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))

            }
        },
        containerColor = Color.White
    )
}
