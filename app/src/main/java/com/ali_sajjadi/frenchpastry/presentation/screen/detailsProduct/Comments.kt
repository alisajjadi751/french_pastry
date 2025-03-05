package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Comment
import com.ali_sajjadi.frenchpastry.ui.theme.body4
import com.ali_sajjadi.frenchpastry.ui.theme.body7
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h5

@Composable
fun Comments(
    comments: List<Comment>,
    sendComment: (String,Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var displayedCommentsCount by remember { mutableIntStateOf(5) }
    val canLoadMore = displayedCommentsCount < comments.size

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent)
    ) {
        SectionTitle("ثبت نظر")

        PostComment({comment , rating->
            sendComment(comment,rating)
        })

        SectionTitle("نظرات کاربران")

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(vertical = 30.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            comments.take(displayedCommentsCount).forEach { item ->
                UserComments(
                    name = item.name,
                    date = item.date,
                    rate = item.rate,
                    comment = item.body
                )
            }

            if (canLoadMore) {
                Icon(painter = painterResource(R.drawable.ic_down2),
                    contentDescription = "بارگذاری بیشتر",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            displayedCommentsCount += 5
                        }
                )
            }
        }
    }
}


@Composable
fun PostComment(
    onClickComment: (String,Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var comment by remember {
        mutableStateOf("")
    }

    var rating by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(value = comment,
            onValueChange = { comment = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xffA09C9C),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black
            ),
            textStyle = MaterialTheme.typography.body4,
            placeholder = {
                Text(
                    text = "نظر خود را وارد کنید",
                    style = MaterialTheme.typography.body4.copy(fontSize = 12.sp),
                    color = Color(0xffA09C9C)
                )
            })

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier,
                onClick = {
                    onClickComment(comment,rating)
                    comment = ""
                    rating = 0
                }
            ) {
                Text(
                    text = "ثبت نظر",
                    style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                    color = Color.White

                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                for (i in 1..5) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star2),
                        contentDescription = null,
                        tint = if (i <= rating) Color(0xffFFC700) else Color.LightGray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { rating = i }

                    )
                }
            }

            Text(
                text = "امتیاز دهید",
                style = MaterialTheme.typography.body4,
                color = Color.Black
            )


        }
    }
}


@Composable
fun UserComments(
    modifier: Modifier = Modifier,
    name: String,
    date: String,
    rate: Int,
    comment: String
) {


    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name, style = MaterialTheme.typography.h5, color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)

            ) {
                Text(
                    text = rate.toString(), style = MaterialTheme.typography.h4, color = Color.Black
                )

                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color(0xffFFC700),
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        Text(
            text = date, style = MaterialTheme.typography.h4, color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffF2F7FD))
                .padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Text(
                text = comment,
                style = MaterialTheme.typography.h4,
                color = Color(0xff555353),
                textAlign = TextAlign.Justify,

                )
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xffA09C9C)
            )

            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "این نظر برای شما مفید  بود؟",
                    style = MaterialTheme.typography.h4,
                    color = Color(0xff555353)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like), contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_dislike), contentDescription = null
                    )

                }
            }

        }
    }


}
