package ru.veresov.paginationstories

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.veresov.paginationstories.util.SegmentedProgressBar

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
private const val DURATION = 5000
private const val TAG = "StoryContentDebug"

@Composable
fun StoryContent(
    modifier: Modifier,
    images: List<String>,
    toNextStory: () -> Unit,
    toPreviousStory: () -> Unit,
    page: Int,
    currentPage: Int
) {

    var indexState by remember { mutableStateOf(0) }
    var isPauseState by remember { mutableStateOf(false) }
    val toNextImage = {
        if (indexState < images.size - 1) {
            indexState++
        } else
            toNextStory()
    }
    val toPreviousImage = {
        if (indexState > 0) {
            indexState--
        } else
            toPreviousStory()
    }

    Box(modifier = modifier
        .background(Color.Black)
        .pointerInput(Unit) {
            val maxWidth = this.size.width
            detectTapGestures(onPress = { offset ->
                val startPress = System.currentTimeMillis()
                isPauseState = true
                this@detectTapGestures.tryAwaitRelease()
                val endPress = System.currentTimeMillis()
                val totalTimeHold = endPress - startPress
                if (totalTimeHold < 200) {
                    val isTapOnRightThreeQuarters = (offset.x > (maxWidth / 4))
                    if (isTapOnRightThreeQuarters) {
                        toNextImage()
                    } else {
                        toPreviousImage()
                    }
                }
                isPauseState = false
            })
        },
        contentAlignment = Alignment.TopCenter,
        content = {
            AsyncImage(
                modifier = modifier,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[indexState])
                    .crossfade(true)
                    .crossfade(200)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    key(indexState) {
                        if (currentPage == page) {
                            SegmentedProgressBar(
                                modifier = modifier
                                    .height(48.dp)
                                    .padding(4.dp, 0.dp)
                                    .weight(1f),
                                duration = DURATION,
                                steps = images.size - 1,
                                currentStep = indexState,
                                paused = isPauseState,
                                onFinished = {
                                    toNextImage()
                                }
                            )
                        }

                    }
                    val context = LocalContext.current
                    IconButton(
                        onClick = {
                            (context as? Activity)?.finish()
                        },
                        content = {
                            Image(
                                modifier = Modifier.size(36.dp),
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(R.string.icon_button_close),
                                contentScale = ContentScale.FillWidth,
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        })
                }
            )
        })
}

@Preview
@Composable
fun PreviewStoryContent() {
    StoryContent(
        modifier = Modifier.fillMaxSize(), listOf(
            "",
            "",
            "",
            "",
            ""
        ),
        toNextStory = {
            Log.i(TAG, "launch to next story")
        },
        toPreviousStory = {
            Log.i(TAG, "return to previous story")
        },
        0,
        0
    )
}
