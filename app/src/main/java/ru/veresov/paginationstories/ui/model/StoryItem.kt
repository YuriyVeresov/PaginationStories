package ru.veresov.paginationstories.ui.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import ru.veresov.paginationstories.data.database.model.Story
import ru.veresov.paginationstories.ui.theme.pinkGradientBrush

/**
 * @author Veresov Yuriy
 * @date 18.09.2023
 */

private const val NORMAL_BORDER = 2
private const val HIDE_BORDER = -1

@Composable
fun StoryItem(modifier: Modifier, story: Story, onStoryClick: () -> Unit) {

    val borderWith = if (story.isViewed) HIDE_BORDER.dp else NORMAL_BORDER.dp
    AsyncImage(
        modifier = modifier
            .size(80.dp)
            .border(BorderStroke(borderWith, pinkGradientBrush), CircleShape)
            .clip(CircleShape)
            .clickable {
                onStoryClick()
            },
        model = ImageRequest.Builder(LocalContext.current)
            .data(story.preview)
            .crossfade(true)
            .crossfade(20)
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.padding(4.dp))
}