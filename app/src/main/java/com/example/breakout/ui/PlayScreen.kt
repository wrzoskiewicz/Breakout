package com.example.breakout.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.breakout.BreakoutScreen
import com.example.breakout.Player
import com.example.breakout.R
import com.example.breakout.controller.PlayerController

@Composable
fun PlayScreen(
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }
    val gameUiState = remember { GameUiState() }
    val playerController = remember {
        PlayerController(
            player = Player(),
            gameUiState = gameUiState
        )
    }

    // Get screen width
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        val speedMultiplier = gameUiState.playerMovementSpeed
                        val newPlayerX =
                            playerController.playerXPosition + dragAmount.x * speedMultiplier

                        // Określenie granic ruchu gracza
                        val leftBoundary = ((-screenWidth + playerController.player.width.dp)/ 2).value
                        val rightBoundary = ((screenWidth - playerController.player.width.dp)/ 2).value

                        // Ogranicz ruch gracza w obrębie granic ekranu
                        playerController.playerXPosition =
                            newPlayerX.coerceIn(leftBoundary, rightBoundary)
                    }

                )
            }
    ) {
        playerController.drawPlayer(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

        IconButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                tint = Color.Black
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Settings")
                },
                text = {
                    Column {
                        Button(
                            onClick = { navController.navigate(BreakoutScreen.Menu.name) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Menu")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { /* TODO: Implement settings action */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Żyroskop")
                        }
                        Button(
                            onClick = { /* TODO: Implement settings action */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Ręcznie")
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}