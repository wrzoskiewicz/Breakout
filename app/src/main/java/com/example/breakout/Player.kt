package com.example.breakout


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import com.example.breakout.controller.PlayerController
import com.example.breakout.ui.GameUiState
import androidx.compose.runtime.*

class Player(
    val width: Float = 40f, // Szerokość gracza
    val height: Float = 10f, // Wysokość gracza
    val color: Color = Color.Red // Kolor gracza
)
