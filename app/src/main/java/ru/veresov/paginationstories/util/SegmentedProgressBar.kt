package ru.veresov.paginationstories.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.rangeTo

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
private const val START_PROGRESS = 0f
private const val END_PROGRESS = 1f
private const val ALPHA_VALUE = .4f

@Composable
fun SegmentedProgressBar(
    modifier: Modifier,
    duration: Int,
    steps: Int,
    currentStep: Int,
    paused: Boolean,
    onFinished: () -> Unit
) {
    val percent = remember { Animatable(START_PROGRESS) }
    LaunchedEffect(paused) {
        if (paused) percent.stop()
        else {
            percent.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = (duration * (END_PROGRESS - percent.value)).toInt(),
                    easing = LinearEasing
                )
            )
            onFinished()
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        for (index in 0.rangeTo(steps)) {
            Row(
                modifier = Modifier
                    .height(4.dp)
                    .clip(RectangleShape)
                    .weight(1f)
                    .background(Color.White.copy(alpha = ALPHA_VALUE))
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxHeight().let {
                            when (index) {
                                currentStep -> it.fillMaxWidth(percent.value)
                                in 0 rangeTo currentStep -> it.fillMaxWidth(END_PROGRESS)
                                else -> it
                            }
                        },
                )
            }
            if (index != steps) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSegmentedProgressBar() {
    SegmentedProgressBar(modifier =
    Modifier
        .height(48.dp)
        .padding(4.dp, 0.dp),
        duration = 1,
        steps = 6,
        currentStep = 3,
        paused = false,
        onFinished = {

        }
    )
}