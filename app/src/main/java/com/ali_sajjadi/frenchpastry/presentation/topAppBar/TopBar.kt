package com.ali_sajjadi.frenchpastry.presentation.topAppBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ali_sajjadi.frenchpastry.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    isBack: Boolean = false
) {
    TopAppBar(
        title = {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu),
                    contentDescription = null,
                    modifier = modifier
                        .size(30.dp)
                )
                Image(
                    painter = painterResource(R.drawable.white_logo),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black)
                )
                Row {
                    NotificationWhitBadge(notification = false)
                    if (isBack){
                        IconButton(onClick = {}) {
                            Image(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }

                }
            }
        }
    )
}
@Composable
fun NotificationWhitBadge(
    modifier: Modifier = Modifier,
    notification: Boolean = false
) {
    Box{
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_notif),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            if (notification){
                Badge(
                    contentColor = Color.Red,
                    modifier = Modifier
                        .size(10.dp)
                        .align(alignment = Alignment.TopCenter)
                )
            }
        }
    }
}

