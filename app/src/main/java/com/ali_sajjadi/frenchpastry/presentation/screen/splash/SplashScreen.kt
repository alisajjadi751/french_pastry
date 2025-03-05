package com.ali_sajjadi.frenchpastry.presentation.screen.splash

import CustomViewModelFactory
import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.viewModel.SplashViewModel
import com.ali_sajjadi.frenchpastry.utils.Constants.SPLASH_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    exitApp: () -> Unit
) {
    val application = LocalContext.current.applicationContext as Application
    val  factory = remember { CustomViewModelFactory{SplashViewModel(application)} }
    val splashViewModel: SplashViewModel = viewModel(factory = factory)

    val showDialog by splashViewModel.showDialog.collectAsState()
    val isInternetAvailable by splashViewModel.isInternetAvailable.collectAsState()
    val isLoggedIn by splashViewModel.isLoggedIn.collectAsState()
    val retryTrigger by splashViewModel.retryTrigger.collectAsState()



    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY)
        // بررسی اتصال اینترنت و وضعیت لاگین
        if (isInternetAvailable) {
             // زمان تاخیر اسپلش اسکرین
            if (isLoggedIn) {
                navigateToHome() // اگر کاربر لاگین کرده باشد به خانه می‌رود
            } else {
                navigateToLogin() // در غیر اینصورت به صفحه لاگین می‌رود
            }
        } else {
            // در صورت عدم اتصال به اینترنت نمایش دایالوگ خطا
            splashViewModel.startSplashScreenDelay(navigateToLogin)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashUi()

        if (showDialog) {

            AlertNet(
                retry = {
                   // viewModel.retryConnection()
                    splashViewModel.triggerRetry()
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
            contentAlignment = Alignment.TopCenter
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
    retry: () -> Unit,
    exit: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = { ConfirmButtons(retry = retry, exit = exit) },
        title = { DialogTitle() },
        containerColor = Color.White
    )
}

@Composable
private fun ConfirmButtons(retry: () -> Unit, exit: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = retry,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "تلاش مجدد", color = Color.White)
        }
        OutlinedButton(
            onClick = exit,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = "خروج از برنامه", color = Color.Black)
        }
    }
}

@Composable
private fun DialogTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(
            text = "لطفا دسترسی به اینترنت را بررسی کنید.",
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            style = TextStyle(fontSize = 20.sp, textDirection = TextDirection.Rtl)
        )
        Image(
            painterResource(id = R.drawable.ic_warning),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }

}


@Preview
@Composable
private fun Show() {
    DialogTitle()
}