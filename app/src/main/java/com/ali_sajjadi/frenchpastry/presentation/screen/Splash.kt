@file:Suppress("UNUSED_EXPRESSION")

package com.ali_sajjadi.frenchpastry.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.Dimens.roundedCornerButton
import com.ali_sajjadi.frenchpastry.presentation.viewModel.ViewModelSplash

@Composable
fun SplashScreen(
    viewModel: ViewModelSplash = viewModel(),
    navigateToLogin: () -> Unit,
    exitApp: () -> Unit
) {
    val showDialog by viewModel.showDialog.collectAsState()
    val isInternetAvailable by viewModel.isInternetAvailable.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startSplashTimer(navigateToLogin)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashUi()

        if (showDialog) {
            AlertNet(
                onDismiss = {},
                retry = {
                    viewModel.retryConnection()
                    if (isInternetAvailable) {
                        viewModel.startSplashTimer(navigateToLogin)
                    }
                },
                exit = exitApp
            )
        }

    }

}

@Composable
fun SplashUi() {

    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {

        Image(
            painter = painterResource(R.drawable.splash_screen_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.white_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 205.dp, height = 121.dp)
                )
                Text(
                    text = "شیرینی فرانسوی",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun AlertNet(
    onDismiss: () -> Unit,
    retry: () -> Unit,
    exit: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.background(Color.Transparent),
        onDismissRequest = {
            onDismiss
        },
        confirmButton = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        retry
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(roundedCornerButton)
                ) {
                    Text(
                        text = "تلاش مجدد",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                OutlinedButton(
                    onClick = {
                        exit
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(roundedCornerButton),
                    border = BorderStroke(width = 1.dp, color = Color.Black)
                ) {
                    Text(
                        text = "خروج از برنامه",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        },
        icon = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painterResource(R.drawable.close),
                    contentDescription = null,
                    Modifier.clickable { }
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "لطفا دسترسی به اینترنت را بررسی کنید.",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                    style = TextStyle(fontSize = 20.sp, textDirection = TextDirection.Rtl),


                    )
                Image(
                    painterResource(id = R.drawable.ic_warning),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        containerColor = Color.White


    )


}


@Preview
@Composable
fun Show() {

}