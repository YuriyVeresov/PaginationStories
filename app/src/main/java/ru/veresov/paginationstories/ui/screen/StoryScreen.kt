package ru.veresov.paginationstories.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.veresov.paginationstories.util.StoryContent

/**
 * @author Veresov Yuriy
 * @date 02.10.2023
 */

private const val EMPTY_LABEL = ""

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryScreen(index: Int, navController: NavController) {
    val storyScreenViewModel: StoryScreenViewModel = koinViewModel()
    val stories = storyScreenViewModel.getStories()

    val pagerState =
        rememberPagerState(if (index > stories.size) 0 else index)
    val scope = rememberCoroutineScope()
    var currentPage by remember { mutableStateOf(pagerState.currentPage) }

    HideSystemUI()

    // Изменяем состояние просмотра
    LaunchedEffect(pagerState.currentPage) {
        storyScreenViewModel.markStoryAsViewed(stories[pagerState.currentPage])
        currentPage = pagerState.currentPage
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxSize(),
        pageCount = stories.size,
        state = pagerState
    ) { page ->
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val pageSize by animateFloatAsState(
            targetValue = if (pageOffset != .0f) .8f else 1f,
            animationSpec = tween(durationMillis = 50),
            label = EMPTY_LABEL
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = pageSize
                    scaleY = pageSize
                },
            contentAlignment = Alignment.Center
        ) {
            StoryContent(
                modifier = Modifier.fillMaxSize(),
                images = stories[page].content,
                toNextStory = {
                    scope.launch {
                        if (pagerState.currentPage < stories.size - 1) {
                            pagerState.animateScrollToPage((pagerState.currentPage + 1) % stories.size)
                        }
                    }
                },
                toPreviousStory = {
                    scope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage((pagerState.currentPage - 1) % stories.size)
                        }
                    }
                },
                page = page,
                currentPage = currentPage,
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun HideSystemUI() {
    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
}