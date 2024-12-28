package com.pixel.azkarcompose.azkar.presentation.sebha

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pixel.azkarcompose.R
import com.pixel.azkarcompose.core.presentation.components.ScreenBackground
import com.pixel.azkarcompose.core.presentation.components.TopLogo
import com.pixel.azkarcompose.ui.theme.Jannalt

@Composable
fun SebhaScreen(
    state: SebhaState,
    onAction: (SebhaAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val doaaList =
        listOf(
            stringResource(R.string.sobhan_allah),
            stringResource(R.string.alhamd_llah),
            stringResource(R.string.la_alah_ala_allah),
            stringResource(R.string.allah_akber),
        )

    val rotationAngle by animateFloatAsState(
        targetValue = state.degreeRotation,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = stringResource(R.string.sebha_rotation), // Customize animation
    )

    // UI Rendering
    ScreenBackground(backgroundResId = R.drawable.bg_tasbeh)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopLogo()
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.sabh_esm_rabya_alaala),
            fontSize = 36.sp,
            fontFamily = Jannalt,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = null,
                    ) {
                        Log.e("gg", "Box Clicked")
                        onAction(SebhaAction.OnClick)
                    },
            contentAlignment = Alignment.Center,
        ) {
            ConstraintLayout(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .rotate(rotationAngle),
            ) {
                val (sebhaHead, sebhaBody) = createRefs()
                Image(
                    painter = painterResource(R.drawable.sebha_head),
                    contentDescription = stringResource(R.string.sebha_head),
                    modifier =
                        Modifier
                            .constrainAs(sebhaHead) {
                                top.linkTo(sebhaBody.top)
                                bottom.linkTo(sebhaBody.top)
                                start.linkTo(sebhaBody.start)
                                end.linkTo(sebhaBody.end)
                            }.padding(bottom = 72.dp),
                )
                Image(
                    painter = painterResource(R.drawable.sebha_body),
                    contentDescription = stringResource(R.string.sebha_body),
                    modifier =
                        Modifier.constrainAs(sebhaBody) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = doaaList[state.sebhaCurrentSpinCount],
                    fontSize = 36.sp,
                    fontFamily = Jannalt,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Text(
                    text = "${state.sebhaCurrentCount}",
                    fontSize = 36.sp,
                    fontFamily = Jannalt,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
@Preview(name = "Sebha")
private fun SebhaScreenPreview() {
    SebhaScreen(
        state = SebhaState(),
        onAction = {},
    )
}
