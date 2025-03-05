package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Comment
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Pastry
import com.ali_sajjadi.frenchpastry.ui.theme.body1
import com.ali_sajjadi.frenchpastry.ui.theme.body2
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Composable
fun DetailsHeader(
    pastry : Pastry,
    comment: List<Comment>,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.padding(top = 10.dp))
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {

       SectionTitle(pastry.title)

        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = pastry.rate.rate.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )

            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(15.dp),
                tint = Color(0xffFFC700)

            )

            CostumeDivider()

            Icon(
                painter = painterResource(R.drawable.ic_favorit),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp, start = 8.dp)
                    .size(25.dp),
                tint = Color.Black
            )
            CostumeDivider()

            Icon(
                painter = painterResource(R.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp, start = 8.dp)
                    .size(25.dp),
                tint = Color.Black
            )

            CostumeDivider()

            CountComment(modifier = modifier.padding(start = 8.dp), commentCount = comment.size)
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        ProductImg(
            img = pastry.thumbnail,
            hasDiscount = pastry.hasDiscount,
            discount = pastry.discountPercent110n
        )

    }

}

@Composable
private fun CostumeDivider() {
    VerticalDivider(
        modifier = Modifier
            .height(20.dp)
            .size(2.dp),
        color = Color.Gray

    )
}


@Composable
fun CountComment(
    commentCount: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_contact_us),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
        )
        if (commentCount > 0) {
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(Color.Black)
                    .size(13.dp)
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = commentCount.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )
            }
        }


    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductImg(
    modifier: Modifier = Modifier,
    img: String = "",
    hasDiscount : Boolean = false,
    discount :String = ""
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            GlideImage(
                model = img,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds
            ) {
                it.placeholder(R.drawable.no_image)
            }

            if (hasDiscount){
                DiscountIcon(
                    discount = discount,
                    modifier = modifier.align(Alignment.BottomStart).padding(start = 20.dp)
                )
            }
        }
    }

}

@Composable
fun DiscountIcon(
    discount:String = "",
    modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .padding(end = 20.dp)
            .width(80.dp)
            .height(50.dp)

    ) {
            Image(
                painter = painterResource(R.drawable.img_off),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "$discount\nتخفیف",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )

    }

}




