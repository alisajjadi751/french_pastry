package com.ali_sajjadi.frenchpastry.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.home.PastryX
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import com.ali_sajjadi.frenchpastry.presentation.screen.home.component.NewComponent
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.ui.theme.h7
import com.ali_sajjadi.frenchpastry.viewModel.HomeViewModel

@Composable
fun NewProduct(
    modifier: Modifier = Modifier,
    title: String,
    products: List<PastryX>,
    homeViewModel: HomeViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_f),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 4.dp)
                )
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.h7.copy(fontSize = 20.sp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End

            ) {
                Text(
                    text = "بیشتر",
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = modifier.padding(end = 5.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.Black

                )

            }
        }

        LazyRow(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
            items(products.size) { item ->
                val product = products[item]

                NewComponent(
                    product = product,
                    homeViewModel = homeViewModel,
                    onClick = {
                        navHostController.navigate(Route.DetailsScreen.withArgs(product.iD.toString()))

                })
            }
        }
    }
}
