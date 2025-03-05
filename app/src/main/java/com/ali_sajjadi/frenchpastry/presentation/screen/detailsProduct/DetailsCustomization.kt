package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Pastry
import com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct.component.DropdownCustomOrder
import com.ali_sajjadi.frenchpastry.ui.theme.body4
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.ui.theme.h8
import com.ali_sajjadi.frenchpastry.viewModel.DetailsProductViewModel

@Composable
fun DetailsCustomization(
    pastry: Pastry,
    detailsViewModel: DetailsProductViewModel,
    modifier: Modifier = Modifier
) {

    var selectedWeight by remember { mutableIntStateOf(1) } // وزن انتخاب شده

    val _finalPrice = pastry.salePrice * selectedWeight
    val _originalPrice = pastry.price.toInt() * selectedWeight


    val finalPrice = detailsViewModel.getFormattedSalePrice(_finalPrice) // محاسبه قیمت نهایی
    val originalPrice = detailsViewModel.getFormattedPrice(_originalPrice.toString()) // محاسبه قیمت اولیه


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionTitle("سفارشی سازی")

        OrderSelector()


        CustomOrder { weight ->
            selectedWeight = weight
        }

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

                if (pastry.hasDiscount){
                    Text(
                        text = originalPrice ,
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Text(
                    text = finalPrice ,
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
    }
}

@Composable
 fun OrderSelector(
     textSize : TextStyle = MaterialTheme.typography.body4
 ) {
    val orderTypes = listOf("Normal" to "سفارش عادی", "Bulk" to "سفارش عمده")
    var selectedOrderType by remember { mutableStateOf("Normal") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            orderTypes.forEach { orderType ->
                val backgroundColor = if (selectedOrderType == orderType.first) {
                    Color.White
                } else {
                    Color.Transparent
                }

                val colorBorder = if (selectedOrderType == orderType.first) {
                    Color.Black
                } else {
                    Color.Gray
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, colorBorder, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(backgroundColor),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    RadioButton(
                        selected = selectedOrderType == orderType.first,
                        onClick = { selectedOrderType = orderType.first },
                        colors = RadioButtonDefaults.colors(Color(0xff24A751))
                    )
                    Text(
                        text = orderType.second,
                        color = Color.Black,
                        style = textSize ,
                    )
                }
            }
        }
    }
}


@Composable
private fun CustomOrder(onWeightChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Text(
                    "وزن شیرینی",
                    color = Color.Black,
                    style = MaterialTheme.typography.body4,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                DropdownCustomOrder(
                    detailsTitle = "1 کیلو",
                    detailsValue = listOf(1..11).flatten().map { it.toString() + " کیلو" },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) { selectedValue ->
                    val weight = selectedValue.split(" ").first().toInt()
                    onWeightChange(weight)
                }
            }
            Row {
                Text(
                    "طعم شیرینی",
                    color = Color.Black,
                    style = MaterialTheme.typography.body4,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                DropdownCustomOrder(
                    detailsTitle = "شکلاتی",
                    detailsValue = listOf("شکلاتی", "خامه ای", "گردویی"),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }

            Row {
                Text(
                    "زمان تحویل",
                    color = Color.Black,
                    style = MaterialTheme.typography.body4,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                DropdownCustomOrder(
                    detailsTitle = "11-14",
                    detailsValue = listOf("11-14", "14-17", "17-20"),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }
}

