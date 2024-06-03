package com.example.breakout.controller

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.breakout.Player
import com.example.breakout.ui.GameUiState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.graphics.graphicsLayer

enum class ControlMode {
    MANUAL, GYROSCOPE
}

var controlMode by mutableStateOf(ControlMode.MANUAL)

class PlayerController(
    internal val player: Player,
    private val gameUiState: GameUiState
) {

    var playerXPosition by mutableStateOf(0f)

    // Metoda do rysowania gracza na ekranie
    @Composable
    fun drawPlayer(modifier: Modifier = Modifier) {
        var isDragging by remember { mutableStateOf(false) }

            if (controlMode == ControlMode.MANUAL) {
            Box(
                modifier = modifier
                    .offset(x = playerXPosition.dp, y = 0.dp)
                    .size(width = player.width.dp, height = player.height.dp)
                    .background(Color.Red)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                Log.d("PlayerController", "Drag started at $offset")
                                isDragging = true
                            },
                            onDrag = { change, dragAmount ->
                                if (isDragging) {
                                    Log.d("PlayerController", "Dragging: $dragAmount")
                                    val speedMultiplier = gameUiState.playerMovementSpeed
                                    playerXPosition += dragAmount.x * speedMultiplier
                                    Log.d("PlayerController", "playerXPosition: $playerXPosition")
                                }
                            },
                            onDragEnd = {
                                Log.d("PlayerController", "Drag ended")
                                isDragging = false
                            }
                        )
                    }
            )
            } else {//implementacja metody poruszania się gracza za pomocą żyroskopu}
        }
    }
}