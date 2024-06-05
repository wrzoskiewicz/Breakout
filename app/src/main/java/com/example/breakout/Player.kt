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

@Composable
fun AnimatePlayer(
    playerController: PlayerController,
    gameUiState: GameUiState,
    Modifier: Modifier
) {
    val animatedPosition by animateFloatAsState(
        targetValue = playerController.playerXPosition,
        finishedListener = {
            // Tutaj możesz wykonać jakieś dodatkowe czynności po zakończeniu animacji, jeśli to konieczne
        }
    )

    Box(
        modifier = Modifier
            .offset(x = animatedPosition.dp, y = 0.dp)
            .size(width = playerController.player.width.dp, height = playerController.player.height.dp)
            .background(playerController.player.color)
            .graphicsLayer {
                translationX = animatedPosition
            }
    )
}