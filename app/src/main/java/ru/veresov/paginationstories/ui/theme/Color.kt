package ru.veresov.paginationstories.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val pinkGradientBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFFE73895),
        Color(0xFFEF476F),
        Color(0xFFF4808A),
        Color(0xFFF9A4B7),
        Color(0xFFFAAAC6)
    ),
    start = Offset.Infinite,
    end = Offset.Zero
)