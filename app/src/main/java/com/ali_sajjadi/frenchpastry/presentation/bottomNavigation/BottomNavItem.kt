package com.ali_sajjadi.frenchpastry.presentation.bottomNavigation

import androidx.annotation.DrawableRes

data class BottomNavItem(
   @DrawableRes val icon : Int,
    val route: String,
    val badge: Boolean = false
)
