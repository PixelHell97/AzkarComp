package com.pixel.azkarcompose.azkar.presentation.quran

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.azkar.presentation.quran.components.RecentSuraCard
import com.pixel.azkarcompose.core.presentation.components.ScreenBackground
import com.pixel.azkarcompose.core.presentation.components.TopLogo
import com.pixel.azkarcompose.ui.theme.AzkarComposeTheme
import com.pixel.azkarcompose.ui.theme.Jannalt
import com.pixel.azkarcompose.ui.theme.primaryDark
import com.pixel.azkarcompose.ui.theme.primaryLight

@Composable
fun QuranScreen(modifier: Modifier = Modifier) {
    ScreenBackground(R.drawable.bg_taj_mahal_agra_india)
    Column(
        modifier = modifier.padding(top = 24.dp).padding(horizontal = 20.dp),
    ) {
        TopLogo()

        var query by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
            },
            label = {
                Text(
                    text = "Sura Name",
                    fontFamily = Jannalt,
                    fontWeight = FontWeight.Bold,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_quran_logo),
                    contentDescription = null,
                    tint = primaryDark,
                    modifier = Modifier.size(28.dp),
                )
            },
            colors =
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryDark,
                    unfocusedBorderColor = primaryLight,
                    focusedContainerColor = Color(0xb3202020),
                    unfocusedContainerColor = Color(0xb3202020),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(6.dp))

        MostRecentlyRead(listOf())
    }
}

@Composable
fun MostRecentlyRead(
    recentSuras: List<String>,
    modifier: Modifier = Modifier,
) {
    if (recentSuras.isEmpty()) {
        Column {
            Text(
                text = "Most Recently",
                fontFamily = Jannalt,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            RecentSuraCard("", {})
            LazyRow {
                items(recentSuras) { recentSura ->
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuranScreenPrev() {
    AzkarComposeTheme {
        QuranScreen()
    }
}
