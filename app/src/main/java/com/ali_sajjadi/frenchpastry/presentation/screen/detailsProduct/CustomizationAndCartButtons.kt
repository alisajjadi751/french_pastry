package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Pastry
import com.ali_sajjadi.frenchpastry.ui.theme.body7
import com.ali_sajjadi.frenchpastry.viewModel.DetailsProductViewModel


@Composable
fun CustomizationAndCartButtons(
    pastry: Pastry,
    detailsViewModel: DetailsProductViewModel,
    modifier: Modifier = Modifier,

    ) {
    var showDialog by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                detailsViewModel.addProductToBasket(
                    basketEntities = BasketEntities(
                        id = pastry.iD,
                        title = pastry.title,
                        image = pastry.thumbnail,
                        price = pastry.price,
                        salePrice = pastry.salePrice,
                        weight = 2
                    )
                )
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_shop),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                )

                Text(
                    text = "افزودن به سبد خرید",
                    style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                    color = Color.White
                )

            }
        }

        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                showDialog = true
            },


            ) {
            Text(
                text = "سفارشی سازی",
                style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                color = Color.Black,
            )
        }

    }
    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.7f))
                .clickable(
                    onClick = { showDialog = false },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() })
        )
        CustomizationAlertDialog(
            pastry,
            detailsViewModel,
            onClose = {
                showDialog = false
            }
        )
    }
}