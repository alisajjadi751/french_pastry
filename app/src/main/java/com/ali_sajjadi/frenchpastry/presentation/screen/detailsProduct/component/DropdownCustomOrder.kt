package com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R
import com.ali_sajjadi.frenchpastry.ui.theme.body4

@Composable
fun DropdownCustomOrder(
    detailsTitle: String = "",
    detailsValue: List<String> = emptyList(),
    modifier: Modifier = Modifier,
    onValueSelected: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf(detailsTitle) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffF0F3FF))
                .padding(8.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedValue,
                color = Color.Black,
                style = MaterialTheme.typography.body4
            )
            Icon(
                painter = painterResource(R.drawable.ic_down),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            detailsValue.forEach { detailsValue ->
                DropdownMenuItem(
                    text = { Text(text = detailsValue) },
                    onClick = {
                        selectedValue = detailsValue
                        expanded = false
                        onValueSelected(detailsValue)
                    }
                )
            }
        }
    }
}
