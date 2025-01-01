package com.pixel.azkarcompose.azkar.presentation.quran.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.ui.theme.AzkarComposeTheme
import com.pixel.azkarcompose.ui.theme.primaryDark

@Composable
fun RecentSuraCard(
    sura: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = primaryDark,
            ),
        modifier = modifier.clickable(onClick = onClick),
    ) {
        Row {
            Column {
                Text(
                    text = "TODO()",
                )
            }
            Image(
                painter = painterResource(R.drawable.bg_recent_read_card),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
            )
        }
    }
}

@Preview
@Composable
private fun RecentSuraPrev() {
    AzkarComposeTheme {
        RecentSuraCard(
            "",
            onClick = {},
        )
    }
}
