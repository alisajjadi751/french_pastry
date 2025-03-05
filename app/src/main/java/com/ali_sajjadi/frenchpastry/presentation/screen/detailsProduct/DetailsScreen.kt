package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import CustomViewModelFactory
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali_sajjadi.frenchpastry.data.DBHandler
import com.ali_sajjadi.frenchpastry.repository.DetailsProductRepository
import com.ali_sajjadi.frenchpastry.repository.ShopRepository
import com.ali_sajjadi.frenchpastry.viewModel.DetailsProductViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    id: Int
) {
    val context = LocalContext.current.applicationContext as Application
    val detailsRepository = DetailsProductRepository()
   val shopRepository = ShopRepository(DBHandler.getDatabase(context))
    val detailsViewModel: DetailsProductViewModel = viewModel(
        factory = CustomViewModelFactory { DetailsProductViewModel(detailsRepository = detailsRepository, application = context, shopRepository = shopRepository ) }
    )

    LaunchedEffect(id) {
        if (detailsViewModel.responseDetails.value == null) {
            detailsViewModel.getDetails(id)
        }
    }
    val detailResponse by detailsViewModel.responseDetails.collectAsState(initial = null)
    val commentResponse by detailsViewModel.responseComment.collectAsState(initial = null)

    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    LaunchedEffect(commentResponse) {
        commentResponse?.message?.let {
            toastMessage = it
            showToast = true
        }
    }
    if (showToast) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
        showToast = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F3FF))
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            detailResponse?.let { response ->
                item {
                    DetailsHeader(pastry = response.pastry, comment = response.pastry.comments)
                }

                item {
                    DetailsCustomization(
                        pastry = response.pastry,
                        detailsViewModel = detailsViewModel
                    )
                }

                item {
                    MaterialsAndDescription(response.pastry.materials)
                }

                item {
                    Comments(
                        comments = response.pastry.comments,
                        sendComment = { content, rate->
                            detailsViewModel.setComment(
                                postId = response.pastry.iD,
                                content = content,
                                rate = rate
                            )
                        }
                    )
                }

                item {
                    SimilarProducts(
                        related = response.pastry.related,
                        detailsViewModel = detailsViewModel,
                        modifier = Modifier.padding(bottom = 50.dp)
                    )
                }
            }
        }
        detailResponse?.let { response ->
            CustomizationAndCartButtons(
                pastry = response.pastry,
                detailsViewModel = detailsViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}