package com.example.breakout.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.breakout.Ball
import com.example.breakout.controller.PlayerController
import kotlinx.coroutines.delay


@Composable
fun AnimateBall(playerController: PlayerController) {
    var ball by remember { mutableStateOf(Ball(100f, 100f, 5f, 5f)) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .offset(x = ball.x.dp, y = ball.y.dp)
                .background(ball.color)
                .size(width = 30.dp, height = 30.dp) // Ustawienie szerokości i wysokości piłki
        )

        LaunchedEffect(Unit) {
            // Ustawienie początkowej pozycji piłki na środku ekranu
            ball = Ball(playerController.playerXPosition, screenHeight.value / 2, 5f, 5f)
            while (true) {
                ball = updateBallPosition(ball, screenWidth, screenHeight, playerController)
                delay(16L) // Ok. 60 fps
            }
        }
    }
}

fun updateBallPosition(
    ball: Ball,
    screenWidth: Dp,
    screenHeight: Dp,
    playerController: PlayerController
): Ball {
    val ballSize = 15.dp // Rozmiar piłki
    val leftBoundary = 0f
    val rightBoundary = screenWidth - ballSize

    var newDx: Float = ball.dx
    var newDy: Float = ball.dy

    var newX: Float = ball.x + newDx
    var newY: Float = ball.y + newDy

    // Kolizja z lewą i prawą krawędzią ekranu
    newX = newX.coerceIn(leftBoundary, rightBoundary.value)
    if (newX == leftBoundary || newX == rightBoundary.value) {
        newDx = -newDx // Odwróć kierunek piłki w przypadku uderzenia w krawędź ekranu
    }

    // Kolizja z górną krawędzią ekranu
    if (newY < 0) {
        newDy = -newDy
        newY = ball.y + newDy
    }

    Log.d("AnimateBall", "pilkaX: $newX")
    Log.d("AnimateBall", "playerX: ${playerController.playerXPosition}")
    // Kolizja z dolną krawędzią ekranu
    if (newY + ballSize.value > screenHeight.value) {
        if (newX-400 > playerController.leftCornerXPosition  && newX-500 < playerController.rightCornerXPosition) {
            newDy = -newDy
            newY = screenHeight.value - ballSize.value // Ustaw, aby znajdowała się tuż nad platformą
        } else {
            // Piłka nie trafia na platformę, reset do środka
            newY = screenHeight.value / 2
            newX = 0f // Ustaw pozycję X na środku ekranu
        }
    }

    return Ball(newX, newY, newDx, newDy, ball.color)
}


