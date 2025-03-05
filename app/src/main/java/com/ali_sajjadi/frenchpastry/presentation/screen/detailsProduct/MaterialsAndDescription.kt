package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.data.remote.model.details.Material
import com.ali_sajjadi.frenchpastry.ui.theme.h4
import com.ali_sajjadi.frenchpastry.ui.theme.h5
import com.ali_sajjadi.frenchpastry.ui.theme.h7

@Composable
fun MaterialsAndDescription(
    material: List<Material>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SectionTitle("مواد به کار رفته در شیرینی")

        Column(
            modifier = Modifier

                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(vertical = 10.dp)

        ) {
            material.forEachIndexed { index, item ->

                Row(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .background(if (index % 2 == 0) Color.White else Color(0xffF0F3FF))


                ) {
                    Materials(
                        material = item.material,
                        materialValue = item.amount
                    )
                }
            }
        }
    }
}

@Composable
private fun Materials(
    material: String,
    materialValue: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = material,
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
        Text(
            text = materialValue,
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
    }
}