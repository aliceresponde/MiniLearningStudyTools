package com.aliceresponde.minilearningstudytools.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface CourseClip {
    data class TextClip(
        val text: String,
        val textColor: Color,
        val bgColor: Color
    ) : CourseClip

    data class IconClip(
        val text: String,
        val textColor: Color,
        val bgColor: Color,
        val icon: ImageVector,
        val contentDescription: String = ""
    ) : CourseClip

    data class BorderClip(
        val text: String,
        val textColor: Color,
        val bgColor: Color,
        val icon: ImageVector,
        val contentDescription: String = "",
        val borderColor: Color,
    ) : CourseClip
}