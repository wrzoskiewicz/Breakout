package com.example.breakout


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Player(
    val width: Float = 40f, // Szerokość gracza
    val height: Float = 10f, // Wysokość gracza
    val color: Color = Color.Red, // Kolor gracza
) {
    // Metoda do rysowania gracza na ekranie
    @Composable
    fun drawPlayer(modifier: Modifier) {
        Box(
            modifier = modifier
                .size(width.dp, height.dp)
                .background(color)
        )
    }
}