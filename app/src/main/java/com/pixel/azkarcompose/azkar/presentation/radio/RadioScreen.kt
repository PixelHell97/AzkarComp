package com.pixel.azkarcompose.azkar.presentation.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.azkar.presentation.radio.components.RadioItemCard
import com.pixel.azkarcompose.core.presentation.components.ScreenBackground
import com.pixel.azkarcompose.core.presentation.components.ShowAlertDialog
import com.pixel.azkarcompose.core.presentation.components.TopLogo
import com.pixel.azkarcompose.core.presentation.util.ObserveAsEvents
import com.pixel.azkarcompose.core.presentation.util.toString
import com.pixel.azkarcompose.ui.theme.Jannalt
import com.pixel.azkarcompose.ui.theme.primaryDark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun RadioScreen(
    state: RadioState,
    event: Flow<RadioChannelEvent>,
    onAction: (RadioAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val tabsList =
        listOf(
            stringResource(R.string.radio),
            stringResource(R.string.favorite),
        )
    val openAlertDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf<String?>(null) }

    ObserveAsEvents(
        events = event,
    ) { uiEvent ->
        when (uiEvent) {
            is RadioChannelEvent.Error -> dialogMessage.value = uiEvent.error.toString(context)
        }
    }

    if (dialogMessage.value != null) {
        ShowAlertDialog(
            onDismissRequest = {
                dialogMessage.value = null
                openAlertDialog.value = false
            },
            onConfirmation = {
                dialogMessage.value = null
                onAction.invoke(RadioAction.ReFetchChannels)
                openAlertDialog.value = false
            },
            dialogTitle = "Error",
            dialogText = dialogMessage.value ?: "",
            icon = Icons.Default.Info,
        )
    }

    // UI Rendering
    ScreenBackground(backgroundResId = R.drawable.bg_silhouette_woman_reading_quran)
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
    ) {
        var selectedIndex by remember { mutableIntStateOf(0) }

        TopLogo()

        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color(0xb3202020),
            indicator = {},
            divider = {},
            modifier = Modifier.clip(RoundedCornerShape(12.dp)),
        ) {
            tabsList.forEachIndexed { index, item ->
                val tabModifier =
                    if (selectedIndex == index) {
                        Modifier
                            .background(
                                color = primaryDark,
                                shape = RoundedCornerShape(12.dp),
                            )
                    } else {
                        Modifier
                    }
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        onTabChange(selectedIndex, onAction)
                    },
                    text = {
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            fontFamily = Jannalt,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    selectedContentColor = Color(0xFF202020),
                    unselectedContentColor = Color.White,
                    modifier = tabModifier,
                )
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else if (state.radioChannels.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text =
                        when (selectedIndex) {
                            SelectedTab.ALL_CHANNELS.ordinal -> "There is no Channels here to list"
                            else -> "You favorite list is empty\n Please add some channels to list"
                        },
                    fontSize = 20.sp,
                    fontFamily = Jannalt,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                )
            }
        } else {
            LazyColumn {
                items(items = state.radioChannels) { radio ->
                    RadioItemCard(
                        radioChannelUi = radio,
                        onPlay = {
                            if (radio.isPlaying) {
                                onAction.invoke(RadioAction.OnStop)
                            } else {
                                onAction.invoke(RadioAction.OnPlay(radio))
                            }
                        },
                        onFavorite = {
                            onAction.invoke(RadioAction.OnFavorite(radio))
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .animateItem(),
                    )
                }
            }
        }
    }
}

private fun onTabChange(
    selectedIndex: Int,
    onAction: (RadioAction) -> Unit,
) {
    when (selectedIndex) {
        SelectedTab.ALL_CHANNELS.ordinal -> {
            onAction.invoke(RadioAction.OnGetAllRadioChannels)
        }

        SelectedTab.FAVORITE_CHANNELS.ordinal -> {
            onAction.invoke(RadioAction.OnGetFavoriteRadioChannels)
        }
    }
}

@Composable
@Preview(name = "Radio")
private fun RadioScreenPreview() {
    RadioScreen(
        state = RadioState(),
        event = emptyFlow(),
        onAction = {},
    )
}
