package com.ali_sajjadi.frenchpastry.presentation.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.ui.theme.h5

@Preview
@Composable
fun StepBar(
    currentStepIndex: Int = 0,
    modifier: Modifier = Modifier
) {

    val item = listOf(
        ItemHeadCart(icon = painterResource(R.drawable.ic_shop), title = "سبد خرید"),
        ItemHeadCart(icon = painterResource(R.drawable.ic_location), title = "انتخاب آدرس"),
        ItemHeadCart(icon = painterResource(R.drawable.ic_card), title = "پرداخت"),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xffF0F3FF)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item.forEachIndexed { index, item ->
            IconCart(
                item = item,
                color = if (index <= currentStepIndex) Color.Black else Color.LightGray,
            )
        }
    }
}

@Composable
fun IconCart(
    item :ItemHeadCart,
    color: Color,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier.size(45.dp),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(R.drawable.backbakethed),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.FillBounds
            )
            Icon(
                painter = item.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = color
            )
        }
        Text(
            text = item.title,
            style = MaterialTheme.typography.h5,
            color = color
        )
    }

}

data class ItemHeadCart(
    val icon: Painter,
    val title: String
)