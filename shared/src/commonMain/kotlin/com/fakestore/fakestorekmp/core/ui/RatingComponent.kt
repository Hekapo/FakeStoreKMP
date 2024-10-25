package com.fakestore.fakestorekmp.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RatingComponent(
    rating: Double,
    maxRating: Int = 5,
    modifier: Modifier = Modifier,
    starColor: Color = MaterialTheme.colorScheme.primary
) {
    val fullStars = rating.toInt()
    val hasHalfStar = (rating - fullStars) in 0.5..0.99
    val emptyStars = maxRating - fullStars - if (hasHalfStar) 1 else 0

    Row(modifier = modifier) {
        repeat(fullStars) {
            StarIcon(icon = Icons.Filled.Star, color = starColor)
        }
        if (hasHalfStar) {
            StarIcon(icon = Icons.AutoMirrored.Filled.StarHalf, color = starColor)
        }
        repeat(emptyStars) {
            StarIcon(icon = Icons.Outlined.StarOutline, color = starColor)
        }
    }
}

@Composable
private fun StarIcon(icon: ImageVector, color: Color) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = Modifier.size(24.dp)
    )
}
