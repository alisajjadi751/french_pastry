package com.ali_sajjadi.frenchpastry.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object SplashScreen : Route(route = "SplashScreen")

    data object LoginScreen : Route(route = "loginScreen")

    data object HomeScreen : Route(route = "homeScreen")

}