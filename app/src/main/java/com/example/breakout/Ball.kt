package com.example.breakout

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Ball(
    var x: Float,
    var y: Float,
    var dx: Float, // Prędkość w poziomie
    var dy: Float, // Prędkość w pionie
    val color: Color = Color.Black // Kolor piłki
)