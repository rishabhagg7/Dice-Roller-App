package com.example.dicerollerjc.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationMenuItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)