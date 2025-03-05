package com.ali_sajjadi.frenchpastry.presentation.screen.home.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.home.PastryX
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.utils.Constants.separator
import com.ali_sajjadi.frenchpastry.viewModel.HomeViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SpecialComponent(
    modifier: Modifier = Modifier,
    product: PastryX,
    homeViewModel: HomeViewModel,
    onClick: (Int) -> Unit
) {

    val price = homeViewModel.getFormattedPrice(product.price)
    val salePrice = homeViewModel.getFormattedSalePrice(product.salePrice)
    Box(
        modifier = modifier
            .height(220.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick(product.iD)
                Log.i("Special1", "SpecialComponent: ${product.iD}")
            }
    ) {
        Card(
            modifier = modifier
                .height(215.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(15.dp))
                .align(Alignment.BottomEnd),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround

            ) {
                GlideImage(
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.FillBounds
                ) {
                    it.placeholder(R.drawable.no_image)
                }

                Text(
                    text = product.title + "\n" + "(1کیلو)",
                    modifier = modifier.fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                )
                if (product.hasDiscount) {

                    Text(
                        text = price,
                        color = Color.Gray,
                        style = MaterialTheme.typography.h6,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = salePrice + "تومان",
                            color = Color.Black,
                            style = MaterialTheme.typography.h6,
                        )
                        Image(
                            painter = painterResource(R.drawable.shopping_cart),
                            contentDescription = null,
                            modifier = modifier
                                .size(30.dp)
                                .clickable { TODO() }
                        )
                    }
                } else {
                    Column {
                        Text(
                            text = ""
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = price + "تومان",
                                color = Color.Black,
                                style = MaterialTheme.typography.h6,
                            )
                            Image(
                                painter = painterResource(R.drawable.shopping_cart),
                                contentDescription = null,
                                modifier = modifier
                                    .size(30.dp)
                                    .clickable { TODO() }
                            )
                        }
                    }
                }
            }
        }
        if (product.hasDiscount) {
            Box(
                modifier = modifier
                    .padding(start = 10.dp)
                    .size(width = 40.dp, height = 20.dp)
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center,

                ) {
                Image(
                    painter = painterResource(R.drawable.img_off),
                    contentDescription = null,
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = product.discountPercentL10n,
                    style = MaterialTheme.typography.h4,
                    color = Color.White
                )
            }
        }
    }
}