package com.pixel.azkarcompose.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pixel.azkarcompose.R

@Composable
fun ScreenBackground(
    backgroundResId: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(backgroundResId),
            contentDescription = "null",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .background(
                    brush =
                    Brush.verticalGradient(
                        colors =
                        listOf(
                            Color(0xb3202020),
                            Color(0xff202020),
                        ),
                    ),
                ),
        )
    }
}

@Preview
@Composable
private fun BackgroundPrev() {
    ScreenBackground(backgroundResId = R.drawable.bg_silhouette_woman_reading_quran)
}
