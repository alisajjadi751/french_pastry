package com.ali_sajjadi.frenchpastry

import CustomViewModelFactory
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.ali_sajjadi.frenchpastry.presentation.bottomNavigation.CustomBottomNavigationBar
import com.ali_sajjadi.frenchpastry.presentation.navgraph.NavGraph
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import com.ali_sajjadi.frenchpastry.presentation.topAppBar.TopBar
import com.ali_sajjadi.frenchpastry.repository.LoginRepository
import com.ali_sajjadi.frenchpastry.ui.theme.FrenchPastryTheme
import com.ali_sajjadi.frenchpastry.viewModel.BottomNavViewModel
import com.ali_sajjadi.frenchpastry.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
   private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()

            val loginRepository = LoginRepository()
            val application = LocalContext.current.applicationContext as Application
            val factory = remember { CustomViewModelFactory{LoginViewModel(loginRepository,application)} }

            val loginViewModel: LoginViewModel = viewModel(factory = factory)

            val isLoggedIn by loginViewModel.isLoggedIn.collectAsState(initial = false)
            val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

           // val startDestination = if (isLoggedIn) Route.HomeScreen.route else Route.SplashScreen.route

            FrenchPastryTheme {
                Scaffold(
                    topBar = {
                        if (isLoggedIn && currentRoute != Route.SplashScreen.route)  {
                            TopBar()
                        }
                             },
                    bottomBar = {
                        if (isLoggedIn && currentRoute != Route.SplashScreen.route)  {
                            CustomBottomNavigationBar(navHostController)
                        }
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        NavGraph(navHostController)
                    }
                }
            }
        }
    }
}

