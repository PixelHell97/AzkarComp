package com.pixel.azkarcompose.azkar.presentation.radio.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.azkar.presentation.models.RadioChannelUi
import com.pixel.azkarcompose.ui.theme.Jannalt
import com.pixel.azkarcompose.ui.theme.inversePrimaryLight

@Composable
fun RadioItemCard(
    radioChannelUi: RadioChannelUi,
    onPlay: () -> Unit,
    onFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor = inversePrimaryLight,
            ),
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(
            text = radioChannelUi.name,
            fontSize = 20.sp,
            fontFamily = Jannalt,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
        )
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center,
        ) {
            if (radioChannelUi.isPlaying) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bg_soundwave),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .clipToBounds()
                            .graphicsLayer(
                                scaleX = 1.4f, // Adjust for zoom or scaling horizontally
                                scaleY = 2.2f, // Adjust for zoom or scaling vertically
                                translationY = 120f, // Adjust this to move the image up or down
                            ),
                    tint = Color(0x4a202020),
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.bg_radio_card),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        space = 32.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = onFavorite,
                ) {
                    Icon(
                        imageVector =
                            if (radioChannelUi.isFavorite) {
                                ImageVector.vectorResource(R.drawable.ic_favorite)
                            } else {
                                ImageVector.vectorResource(R.drawable.ic_unfavorite)
                            },
                        contentDescription = "Add to favorite",
                        modifier =
                            Modifier
                                .size(32.dp),
                        tint = Color(0xFF202020),
                    )
                }
                IconButton(
                    onClick = onPlay,
                ) {
                    Icon(
                        imageVector =
                            if (radioChannelUi.isPlaying) {
                                ImageVector.vectorResource(R.drawable.ic_pause)
                            } else {
                                ImageVector.vectorResource(R.drawable.ic_play)
                            },
                        contentDescription = "Play/Pause button",
                        modifier =
                            Modifier
                                .size(32.dp),
                        tint = Color(0xFF202020),
                    )
                }
            }
        }
    }
}

@Preview(name = "RadioItemCard", device = "id:Galaxy Nexus")
@Composable
private fun PreviewRadioItemCard() {
    RadioItemCard(
        radioChannelUi =
            RadioChannelUi(
                1,
                "Radio Ibrahim Al-Akdar",
                "",
                isPlaying = false,
            ),
        onPlay = {},
        onFavorite = {},
    )
}

@Preview(name = "RadioItemCardPlaying", device = "id:Galaxy Nexus")
@Composable
private fun PreviewRadioItemCardPlaying() {
    RadioItemCard(
        radioChannelUi =
            RadioChannelUi(
                1,
                "Radio Ibrahim Al-Akdar",
                "",
                isPlaying = true,
            ),
        onPlay = {},
        onFavorite = {},
    )
}
