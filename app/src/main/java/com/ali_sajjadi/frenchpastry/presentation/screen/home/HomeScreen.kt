package com.ali_sajjadi.frenchpastry.presentation.screen.home

import CustomViewModelFactory
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.presentation.screen.home.component.CustomCakeProduct
import com.ali_sajjadi.frenchpastry.repository.HomeRepository

import com.ali_sajjadi.frenchpastry.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeRepository: HomeRepository = HomeRepository()
    ) {
    // ساخت Factory فقط یک بار
    val factory = remember { CustomViewModelFactory { HomeViewModel(homeRepository) } }

    // استفاده از ViewModel
    val homeViewModel: HomeViewModel = viewModel(factory = factory)


    val homeResponse by homeViewModel.responseMain.collectAsState(initial = null)

    val filteredNews = homeResponse?.pastries?.find { it.iD == "news" }
    val filteredSpecial = homeResponse?.pastries?.find { it.iD == "special" }
    val filteredTopRate = homeResponse?.pastries?.find { it.iD == "top_rated" }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F3FF)),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        homeResponse?.let { response ->


            item {
                TopSlider(
                    imageUrls = response.sliders
                )
            }
            item {
                filteredNews?.let { pastry ->
                    NewProduct(
                        title = pastry.title,
                        products = pastry.pastries,
                        homeViewModel = homeViewModel,
                        navHostController = navHostController
                    )
                }
            }
            item {
                filteredSpecial?.let { pastry ->
                    SpecialProducts(
                        title = pastry.title,
                        products = pastry.pastries,
                        homeViewModel = homeViewModel,
                        navHostController = navHostController
                    )
                }
            }
            item {
                filteredTopRate?.let { pastry ->
                    TopRateProduct(
                        title = pastry.title, products = pastry.pastries,
                        homeViewModel = homeViewModel,
                        navHostController = navHostController
                    )
                }
            }
            item {
                CustomCakeProduct()
            }
        } ?: run {
            // نمایش یک وضعیت بارگذاری یا پیام خطا در صورت نبود داده
            item { LoadingOrErrorIndicator(homeResponse == null) }
        }
    }
}

@Composable
fun LoadingOrErrorIndicator(isLoading: Boolean) {
    if (isLoading) {
        // نمایش پیام "در حال بارگذاری"
        Text(text = "Loading data...", modifier = Modifier.padding(16.dp))
    } else {
        // نمایش پیام خطا
        Text(text = "Error fetching data.", modifier = Modifier.padding(16.dp))
    }
}
