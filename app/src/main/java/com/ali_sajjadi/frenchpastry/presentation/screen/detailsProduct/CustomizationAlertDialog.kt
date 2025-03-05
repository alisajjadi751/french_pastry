package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Pastry
import com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct.component.DropdownCustomOrder
import com.ali_sajjadi.frenchpastry.ui.theme.body7
import com.ali_sajjadi.frenchpastry.ui.theme.h3
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.ui.theme.h8
import com.ali_sajjadi.frenchpastry.viewModel.DetailsProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationAlertDialog(
    pastry: Pastry,
    detailsViewModel: DetailsProductViewModel,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedWeight by remember { mutableIntStateOf(1) }

    val _finalPrice = pastry.salePrice * selectedWeight
    val _originalPrice = pastry.price.toInt() * selectedWeight


    val finalPrice = detailsViewModel.getFormattedSalePrice(_finalPrice) // محاسبه قیمت نهایی
    val originalPrice =
        detailsViewModel.getFormattedPrice(_originalPrice.toString()) // محاسبه قیمت اولیه


    BasicAlertDialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .width(290.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.close),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.clickable {
                    onClose()
                }
            )
            OrderSelector(
                textSize = MaterialTheme.typography.h3
            )

            CustomOrder1({
                selectedWeight = it
            })

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "مبلغ نهایی",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = originalPrice,
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = finalPrice,
                        style = MaterialTheme.typography.h8,
                        color = Color(0xff24A751),
                    )
                    Text(
                        text = "تومان",
                        style = MaterialTheme.typography.h5,
                        color = Color.Black
                    )
                }
            }

            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {}
            ) {
                Text(
                    text = "افزودن به سبد خرید",
                    style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomOrder1(
    selectedWeight: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val detailValue = listOf("شکلاتی", "کاراملی")

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, color = Color(0xffA09C9C), RoundedCornerShape(10.dp))
            .padding(vertical = 20.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                        append("وزن شیرینی")
                    }
                    withStyle(style = MaterialTheme.typography.h4.toSpanStyle()) {
                        append("(کیلوگرم)")
                    }
                },
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            AdjustCount(modifier = Modifier.weight(0.4f), countValue = {
                selectedWeight(it)
            })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                        append("تعداد طبقه")
                    }
                    withStyle(
                        style = MaterialTheme.typography.h4.toSpanStyle().copy(color = Color.Red)
                    ) {
                        append("(اگر کیک بود)")
                    }

                },
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            AdjustCount(modifier = Modifier.weight(0.4f))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "طعم شیرینی",
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            DropdownCustomOrder(
                detailsTitle = "شکلاتی",
                detailsValue = detailValue
            )
        }
    }
}

@Preview
@Composable
fun AdjustCount(
    countValue: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(1) }

    Row(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffF0F3FF))
                .clickable {
                    if (count <= 9) {
                        count++
                    }
                    countValue(count)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color(0xff292D32),
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.h5,
            color = Color.Black,
        )
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffF0F3FF))
                .clickable {
                    if (count in 2..10) {
                        count--
                    }
                    countValue(count)
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_minimize2),
                contentDescription = null,
                tint = Color(0xff292D32),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

