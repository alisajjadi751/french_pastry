package com.ali_sajjadi.frenchpastry.presentation.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R

@Composable
fun CustomCakeProduct(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.img_custom_cake),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(end = 20.dp, start = 20.dp),
        contentScale = ContentScale.FillBounds
    )
}