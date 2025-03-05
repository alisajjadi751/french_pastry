package com.ali_sajjadi.frenchpastry.presentation.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun TimerBox(
    totalTime: Int,
    onTimeout: () -> Unit
) {
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0) {
            delay(1000)
            currentTime -= 1
        }else onTimeout()
    }
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray.copy(0.25f))
                .size(55.dp, 32.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = if (currentTime > 0) "${currentTime / 60}:${currentTime % 60}" else "00:00",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
        }
    }
