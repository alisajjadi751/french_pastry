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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.Dimens.mediumText
import com.ali_sajjadi.frenchpastry.presentation.Dimens.roundedCornerButton
import com.ali_sajjadi.frenchpastry.presentation.Dimens.smallText
import com.ali_sajjadi.frenchpastry.presentation.screen.login.CustomTextField


@Composable
fun UsernameAlertDialog() {

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
                        text = "ثبت اطلاعات",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = smallText
                        )
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
                        }
                )
                Spacer(Modifier.padding(top = 8.dp))
                Text(
                    text = "اطلاعات کاربری",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = mediumText,
                        fontWeight = FontWeight.Bold,
                        textDirection = TextDirection.Rtl
                    )
                )
                CustomTextField(
                    value = "",
                    onValueChange = { newValue ->

                    },
                    placeholder = "نام و نام خانوادگی",
                )
            }
        },
        containerColor = Color.White
    )
}

@Preview
@Composable
fun Ui() {
    UsernameAlertDialog()
}
