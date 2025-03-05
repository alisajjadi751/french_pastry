package com.ali_sajjadi.frenchpastry.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object SplashScreen : Route(route = "SplashScreen")

    data object LoginScreen : Route(route = "loginScreen")

    data object HomeScreen : Route(route = "homeScreen")
    data object CakeScreen : Route(route = "cakeScreen")
    data object CartScreen : Route(route = "cartScreen")
    data object PastryScreen : Route(route = "pastryScreen")
    data object UserScreen : Route(route = "userScreen")

    data object DetailsScreen: Route(route = "detailsScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}