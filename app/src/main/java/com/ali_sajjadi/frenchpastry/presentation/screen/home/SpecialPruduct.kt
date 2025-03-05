package com.ali_sajjadi.frenchpastry.presentation.screen.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.ui.theme.h8
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.ali_sajjadi.frenchpastry.data.remote.model.home.PastryX
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import com.ali_sajjadi.frenchpastry.presentation.screen.home.component.SpecialComponent
import com.ali_sajjadi.frenchpastry.viewModel.HomeViewModel


@Composable
fun SpecialProducts(
    modifier: Modifier = Modifier,
    title: String,
    products:List<PastryX>,
    homeViewModel: HomeViewModel,
    navHostController: NavHostController
) {
    GradientRectangleTB()
    Box(
        modifier = modifier
            .height(260.dp)
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xff532379),
                        Color(0xff3C0E52)
                    ), radius = 600f
                )
            )
    ) {
        Row (
            modifier = modifier
                .fillMaxSize()
        ){
            Box(
                modifier = modifier
                    .padding(start = 10.dp)
                    .weight(2f),
                contentAlignment = Alignment.TopStart
            ){
                Banner(text = title)
            }
            Box(
                modifier = modifier
                    .weight(3f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ){
                LazyRow(
                    modifier = modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                ) {
                    items(products.size){item->
                        val product = products[item]

                        SpecialComponent(
                            product = product,
                            homeViewModel = homeViewModel ,
                            onClick = {
                                navHostController.navigate(Route.DetailsScreen.withArgs(product.iD.toString()))
                            }
                        )

                    }
                }
            }
        }
    }
    GradientRectangleTB()
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(
        modifier = modifier
            .height(260.dp)
            .width(160.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .height(200.dp),
            contentAlignment = Alignment.TopCenter


        ) {
            Box(
                modifier = modifier
                    .height(190.dp)
                    .width(155.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                )
                DynamicText(text)
            }

        }
        MoreText()
        Spacer(modifier = modifier.padding(4.dp))
        PreviewCustomEllipse()
    }
}

@Composable
fun DynamicText(text: String, modifier: Modifier = Modifier) {
    val words = text.split("\\s+".toRegex()).filter { it.isNotEmpty() }

    Text(
        buildAnnotatedString {
            words.forEachIndexed { index, word ->
                withStyle(
                    style = if (index % 2 != 0) {
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xffFFC500),
                                    Color(0xffFF9A00)
                                )
                            )
                        )
                    } else {
                        SpanStyle(color = Color.White)
                    }
                ) {
                    append(word)
                }

                if (index != words.lastIndex) {
                    append("\n")
                }
            }
        },
        modifier = modifier
            .padding(start = 5.dp, top = 15.dp),
        style = MaterialTheme.typography.h8,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MoreText(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "بیشتر",
            color = Color(0xffF0F2FF),
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = modifier.padding(end = 5.dp))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = Color.White

        )

    }
}

@Composable
fun CustomEllipse(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        drawOval(

            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0x96272626),
                    Color(0x00000000)
                ),
                center = center,
                radius = size.maxDimension /1.5f
            ),
            topLeft = Offset.Zero,
            size = Size(width,height)
        )
    }
}

@Composable
fun PreviewCustomEllipse() {
    Box(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        CustomEllipse(
            modifier = Modifier
                .width(160.dp)
                .height(20.dp)
        )
    }
}

@Preview
@Composable
fun GradientRectangleTB() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
    ) {

        GradientRectangle(
            modifier = Modifier.fillMaxSize(),
            colors = listOf(Color(0xff4D076D),
                Color(0xffF5F5F5)),
            radiusFactor = 2f,
        )
    }
}

@Composable
fun GradientRectangle(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(Color(0xff4D076D), Color(0xffF5F5F5)),
    radiusFactor: Float = 2f,
) {
    Canvas(modifier = modifier) {
        drawRect(
            brush = Brush.radialGradient(
                colors = colors,
                center = center,
                radius = size.maxDimension / radiusFactor
            ),
            size = size
        )
    }
}






