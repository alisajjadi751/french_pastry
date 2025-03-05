package com.ali_sajjadi.frenchpastry.presentation.bottomNavigation

import CustomViewModelFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import com.ali_sajjadi.frenchpastry.viewModel.BottomNavViewModel


@Composable
fun CustomBottomNavigationBar(
    navController: NavHostController
) {
    val factory = remember { CustomViewModelFactory { BottomNavViewModel() } }
    val bottomNavViewModel: BottomNavViewModel = viewModel(factory = factory)

    val currentRoute by bottomNavViewModel.currentRoute.collectAsState()

    val items = BottomNavItems.items
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(60.dp),
        contentAlignment = Alignment.BottomCenter // اطمینان از مرکز بودن محتوای اصلی
    ) {

        Row(
            modifier = Modifier
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom // چینش آیتم‌ها در پایین
        ) {
            items.forEachIndexed { index, item ->
                if (index != 2) {
                    NavigationBarItem(

                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                bottomNavViewModel.updateCurrentRoute(item.route)
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center

                            ) {
                                if (currentRoute == item.route) {
                                    GradientBackgroundIcon()
                                }
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null,
                                    tint = Color.Black,//if (currentRoute == item.route) Color.Black else Color.Gray,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f)) // فاصله مناسب برای آیکون وسط
                }

            }

        }
        Box(
            modifier = Modifier
                .offset(y = -20.dp) // بالا بردن آیکون مرکزی
                .size(80.dp),
            contentAlignment = Alignment.Center // اطمینان از مرکز بودن
        ) {
            Image(
                painter = painterResource(R.drawable.ic_bg_shop),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            IconButton(
                onClick = {
                    bottomNavViewModel.updateCurrentRoute(Route.CartScreen.route)
                    navController.navigate(Route.CartScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shop),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun GradientBackgroundIcon() {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color(0xff4D076D)),

                )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.LightGray.copy(alpha = 0.5f),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}
