package com.ali_sajjadi.frenchpastry.presentation.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ali_sajjadi.frenchpastry.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.delay


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopSlider(
    imageUrls: List<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        imageUrls.size
    }
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % imageUrls.size
            if (pagerState.currentPage == imageUrls.lastIndex) {
                pagerState.scrollToPage(0)
            } else {
                pagerState.animateScrollToPage(
                    nextPage,
                    animationSpec = tween(durationMillis = 1000)
                )
            }
        }
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
            .height(220.dp)

    ) {
        val (box1, box2) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = Color(0x564CC726),
                    clip = false
                )
                .background(Color.Transparent)
                .clip(RoundedCornerShape(15.dp))
                .constrainAs(box1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                val imgUrl = imageUrls[page]
                GlideImage(
                    model = imgUrl,
                    contentDescription = "image $imgUrl",
                    modifier = Modifier
                        .height(220.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.FillBounds
                ) {
                    it.placeholder(R.drawable.no_image)
                }
            }
        }
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .constrainAs(box2) {
                    start.linkTo(box1.start)
                    end.linkTo(box1.end)
                    top.linkTo(box1.bottom)
                    bottom.linkTo(box1.bottom)
                }
        ) {
            PageIndicator(
                pageSize = imageUrls.size,
                activePage = pagerState.currentPage
            )
        }
    }
}

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    activePage: Int,
    activeColor: Color = Color.Black,
    inactiveColor: Color = Color(0xffD9D9D9)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = if (page == activePage) activeColor else inactiveColor)
            )
        }
    }
}
