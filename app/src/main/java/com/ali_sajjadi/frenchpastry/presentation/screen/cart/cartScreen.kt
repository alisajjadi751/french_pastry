package com.ali_sajjadi.frenchpastry.presentation.screen.cart

import CustomViewModelFactory
import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.DBHandler
import com.ali_sajjadi.frenchpastry.repository.ShopRepository
import com.ali_sajjadi.frenchpastry.ui.theme.body4
import com.ali_sajjadi.frenchpastry.ui.theme.body7
import com.ali_sajjadi.frenchpastry.viewModel.CartViewModel
import kotlinx.coroutines.launch

@Composable
fun CartScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = {3})
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffF0F3FF))
           .padding(top = 20.dp, end = 20.dp, start = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        StepBar(currentStepIndex = pagerState.currentPage)


        HorizontalPager(
            state = pagerState
        ) {page->
            when(page){
                0 -> CartItem(
                    onNext = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)

                        }
                    }
                )
                1 -> UserAddress()

            }
        }

    }
}



