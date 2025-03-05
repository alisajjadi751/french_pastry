package com.ali_sajjadi.frenchpastry.presentation.bottomNavigation

import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route

object BottomNavItems {
    val items = listOf(
        BottomNavItem(R.drawable.ic_home, Route.HomeScreen.route),
        BottomNavItem(R.drawable.ic_cake, Route.CakeScreen.route),
        BottomNavItem(R.drawable.ic_shop, Route.CartScreen.route),
        BottomNavItem(R.drawable.ic_pastry, Route.PastryScreen.route),
        BottomNavItem(R.drawable.ic_user,Route.UserScreen.route)
        )
}