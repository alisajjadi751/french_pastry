package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Related
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.viewModel.DetailsProductViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun SimilarProducts(
    related: List<Related>,
    detailsViewModel: DetailsProductViewModel,
    modifier: Modifier = Modifier
) {




    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle("محصولات مشابه")

        related.forEach {
            val price = detailsViewModel.getFormattedSalePrice( it.price)
            val salePrice = detailsViewModel.getFormattedSalePrice(it.salePrice)
            Log.i("AAA",salePrice.toString())
            Log.i("InitialSimilar", "Item: $it")
            InitialSimilar(
                img = it.thumbnail,
                name = it.title,
                price = price,
                salePrice = salePrice
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InitialSimilar(
    img: String,
    name: String,
    price: String,
    salePrice: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .wrapContentHeight()
            .width(250.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = img,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(250.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(15.dp)),
            contentScale = ContentScale.FillBounds
        ) {
            it.placeholder(R.drawable.no_image)
        }
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = price.toString(),
                style = MaterialTheme.typography.h5,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
            Text(
                text = salePrice.toString() + "تومان",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
            )
        }
    }
}
