package ru.veresov.paginationstories.model

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
data class Story(
    val preview: String,
    val content: List<String>,
    val isViewed: Boolean
)