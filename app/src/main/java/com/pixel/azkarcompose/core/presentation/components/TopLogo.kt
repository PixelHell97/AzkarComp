package com.pixel.azkarcompose.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.ui.theme.Kamali
import com.pixel.azkarcompose.ui.theme.primaryDark

@Composable
fun TopLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.mosque_011),
            contentDescription = null,
        )

        Text(
            text = stringResource(R.string.islami),
            textAlign = TextAlign.Center,
            fontSize = 80.sp,
            fontFamily = Kamali,
            fontWeight = FontWeight.Normal,
            color = primaryDark,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}