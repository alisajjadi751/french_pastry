package com.ali_sajjadi.frenchpastry.presentation.screen.cart

import CustomViewModelFactory
import android.app.Application
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.DBHandler
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import com.ali_sajjadi.frenchpastry.presentation.screen.cart.component.CustomBasicTextField
import com.ali_sajjadi.frenchpastry.repository.ShopRepository
import com.ali_sajjadi.frenchpastry.ui.theme.body1
import com.ali_sajjadi.frenchpastry.ui.theme.body2
import com.ali_sajjadi.frenchpastry.ui.theme.body4
import com.ali_sajjadi.frenchpastry.ui.theme.body5
import com.ali_sajjadi.frenchpastry.ui.theme.body7
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.ui.theme.h6
import com.ali_sajjadi.frenchpastry.utils.Constants.separator
import com.ali_sajjadi.frenchpastry.viewModel.CartViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun CartItem(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current.applicationContext as Application

    val shopRepository = ShopRepository(DBHandler.getDatabase(context))
    val factory = remember { CustomViewModelFactory { CartViewModel(shopRepository) } }
    Log.i("FACTORY1",factory.toString())
    val cartViewModel: CartViewModel = viewModel(factory = factory)

    val cartResponse by cartViewModel.cartItem.collectAsState(initial = emptyList())
    val salePrice by cartViewModel.salePrice.collectAsState()
    val discount by cartViewModel.discount.collectAsState()

    val showDeleteDialog by cartViewModel.showDeleteDialog.collectAsState()





    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {

            if (cartResponse.isNotEmpty()) {

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp,
                        ),
                        shape = RoundedCornerShape(15.dp)

                    ) {
                        Column {
                            cartResponse.forEachIndexed { index, item ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    ProductsItem(
                                        items = item,
                                        isLastIndex = index == cartResponse.lastIndex,
                                        onClick = {
                                            cartViewModel.showDeleteDialog(item)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            } else {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "سبد خرید خالی است",
                            color = Color.Gray
                        )
                    }
                }
            }

            item {
                DiscountCode()
            }

            item {
                FinalPrice(
                    salePrice = salePrice,
                    discount = discount
                )

            }
            item {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .fillMaxWidth()
                        .height(40.dp),
                    enabled = cartResponse.isNotEmpty(),
                    onClick = {
                        onNext()
                    }
                ) {
                    Text(
                        text = "انتخاب آدرس",
                        style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                        color = Color.White

                    )
                }
            }
        }

    }
    showDeleteDialog?.let { dialogItem ->
        DeleteItem(
            onClose = { cartViewModel.closeDeleteDialog() },
            delete = { item ->
                cartViewModel.deleteItems(item)
                cartViewModel.closeDeleteDialog()
            },
            item = dialogItem
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProductsItem(
    items: BasketEntities,
    isLastIndex: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = items.title,
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close_square),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onClick()
                    },

                )

            GlideImage(
                model = items.image,
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    if (items.price.toInt() != items.salePrice) {
                        Text(
                            text = separator.format(items.price.toInt() / 10),
                            style = MaterialTheme.typography.body4,
                            color = Color(0xff706C6C),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    Text(
                        text = separator.format(items.salePrice / 10) + "تومان",
                        style = MaterialTheme.typography.body5,
                        color = Color.Black
                    )
                }
            }
        }
        if (!isLastIndex) {

            DashedHorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteItem(
    onClose: () -> Unit,
    delete: (BasketEntities) -> Unit,
    item: BasketEntities,
    modifier: Modifier = Modifier
) {

    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Column(
            modifier = modifier
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
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onClose()
                    }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_cancel),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)

                )
                Text(
                    text = "آیا از حذف این محصول اطمینان دارید؟",
                    style = MaterialTheme.typography.body4,
                    color = Color.Black
                )
            }

            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = {
                    delete(item)
                    onClose()
                }
            ) {
                Text(
                    text = "حذف محصول",
                    style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                    color = Color.White
                )
            }

            OutlinedButton(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                onClick = {
                    onClose()
                }
            ) {
                Text(
                    text = "برگشت به سبد خرید",
                    style = MaterialTheme.typography.body7.copy(fontSize = 14.sp),
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun FinalPrice(
    discount: Int,
    salePrice: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(vertical = 15.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "تخفیف",
                style = MaterialTheme.typography.h5.copy(fontSize = 16.sp, color = Color.Black)
            )
            Text(
                text = separator.format(discount / 10),
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 20.sp,
                    color = Color(0xffCF3C3C)
                )

            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.h5.toSpanStyle()
                            .copy(fontSize = 16.sp, color = Color.Black)
                    ) {
                        append("مبلغ نهایی")
                    }
                    withStyle(
                        style = MaterialTheme.typography.h5.toSpanStyle().copy(color = Color.Black)
                    ) {
                        append("(تومان)")
                    }
                },

                )
            Text(
                text = separator.format(salePrice / 10),
                style = MaterialTheme.typography.h5.copy(fontSize = 24.sp, color = Color.Black)

            )

        }
    }

}

@Composable
fun DiscountCode(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        PreviewCustomBasicTextField(
            modifier = modifier.weight(0.7f)
        )

        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .weight(0.3f),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            contentPadding = PaddingValues(0.dp),
            onClick = {

            }
        ) {
            Text(
                text = "اعمال تخفیف",
                style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                color = Color.Black
            )
        }
    }

}

@Composable
fun PreviewCustomBasicTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    CustomBasicTextField(
        text = text,
        onTextChanged = { text = it },
        placeholder = "کد تخفیف",
        backgroundColor = Color.White,
        borderColor = Color.LightGray,
        textColor = Color.Black,
        placeholderColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    )
}

@Composable
fun DashedHorizontalDivider(
    color: Color = Color.LightGray,
    thickness: Dp = 2.dp,
    dashLength: Dp = 3.dp,
    gapLength: Dp = 2.dp,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Canvas(modifier = modifier.height(thickness)) {
        val canvasWidth = size.width
        val dashLengthPx = dashLength.toPx()
        val gapLengthPx = gapLength.toPx()
        val thicknessPx = thickness.toPx()

        var startX = 0f
        while (startX < canvasWidth) {
            drawLine(
                color = color,
                start = Offset(x = startX, y = thicknessPx / 2),
                end = Offset(x = startX + dashLengthPx, y = thicknessPx / 2),
                strokeWidth = thicknessPx
            )
            startX += dashLengthPx + gapLengthPx
        }
    }
}


