package com.ali_sajjadi.frenchpastry.presentation.screen.login


import CustomViewModelFactory
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.Dimens.largeText
import com.ali_sajjadi.frenchpastry.presentation.Dimens.mediumText
import com.ali_sajjadi.frenchpastry.presentation.Dimens.roundedCornerButton
import com.ali_sajjadi.frenchpastry.presentation.Dimens.smallText
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import com.ali_sajjadi.frenchpastry.presentation.screen.login.alertDialog.AlertVerify
import com.ali_sajjadi.frenchpastry.repository.LoginRepository
import com.ali_sajjadi.frenchpastry.ui.theme.TextColorLight
import com.ali_sajjadi.frenchpastry.ui.theme.body3
import com.ali_sajjadi.frenchpastry.ui.theme.body4
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.ui.theme.h8
import com.ali_sajjadi.frenchpastry.viewModel.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
   navController: NavHostController,
    loginRepository: LoginRepository = LoginRepository(),
) {

    val application = LocalContext.current.applicationContext as Application
    val factory = remember { CustomViewModelFactory{LoginViewModel(loginRepository,application)} }
    val loginViewModel: LoginViewModel = viewModel(factory = factory)

    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState(initial = false)

    val phone by loginViewModel.phone.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var isPhoneValid by remember { mutableStateOf(true) }
    val verifyCodeResponse by loginViewModel.verifyCode.collectAsState(initial = null)

    val coroutineScope = rememberCoroutineScope()

    if (isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.LoginScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(color = 0xFFF0F3FF),
                        Color(color = 0xFFFFFFFF)
                    )
                )
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (img1, img2, img3, img4, img5, box) = createRefs()

            Image(
                painter = painterResource(R.drawable.flower2),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(img1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .size(width = 202.dp, height = 132.dp)
            )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(img2) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 40.dp)
            )
            Image(
                painter = painterResource(R.drawable.white_logo),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .constrainAs(img3) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(img1.bottom)
                        bottom.linkTo(img1.bottom)
                    }
                    .size(width = 205.dp, height = 121.dp)
            )
            Image(
                painter = painterResource(R.drawable.sweets),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(img4) {
                        end.linkTo(parent.end)
                        top.linkTo(img3.bottom)
                    }
                    .size(width = 144.dp, height = 181.dp),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.sweet),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(img5) {
                        end.linkTo(img4.start)
                        top.linkTo(img3.bottom)
                    }
                    .size(width = 81.dp, height = 115.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .constrainAs(box) {
                        start.linkTo(parent.start)
                        top.linkTo(img2.bottom)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 20.dp, start = 20.dp),
                ) {
                    Text(
                        text = "ورود به برنامه",
                        style = MaterialTheme.typography.h5.copy(fontSize = 20.sp)
                    )
                    Text(
                        text = "شیرینی فرانسوی",
                        modifier = Modifier
                            .padding(top = 5.dp),
                        style = MaterialTheme.typography.h8.copy(fontSize = 30.sp)
                    )
                    Text(
                        text = "جهت ورود به برنامه شماره موبایل خود را در کادر زیر وارد کرده و کد ارسالی به موبایل خود را در برنامه وارد کنید.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        style = MaterialTheme.typography.body3
                        ,
                        color = TextColorLight
                    )
                    CustomTextField(
                        value = phone,
                        onValueChange = { newValue ->
                            if (newValue.length <= 11) {
                                loginViewModel.updatePhone(newValue)
                            }

                            isPhoneValid = newValue.length == 11 && newValue.startsWith("09")
                        },
                        placeholder = "شماره موبایل خود را وارد کنید",
                        errorMessage = if (!isPhoneValid) "شماره موبایل معتبر نیست" else "",
                        isError = !isPhoneValid && phone.length == 11,
                        viewModel = loginViewModel
                    )

                    Button(
                        onClick = {
                            if (isPhoneValid && phone.isNotEmpty()) {
                                loginViewModel.sendPhone2(phone)
                                showDialog = true
                            }
                        },
                        modifier = Modifier
                            .width(260.dp)
                            .padding(top = 20.dp)
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(roundedCornerButton)
                    ) {
                        Text(
                            text = "ارسال کد به شماره موبایل من",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
    if (showDialog) {
        AlertVerify(onDismiss = { showDialog = false })
    }

    verifyCodeResponse?.let { response ->
        if (response.success == 1) {
            showDialog = false
            coroutineScope.launch {
                loginViewModel.saveLoginStatus(true)
                navController.navigate(Route.HomeScreen.route)
            }
        }
    }
}
