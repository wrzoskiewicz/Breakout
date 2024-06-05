package com.example.breakout.ui
import com.example.breakout.controller.ControlMode

data class GameUiState(
    var playerMovementSpeed: Float = 0.6f,
    var playerMovementSpeedGyroscop: Float = 6f,
    var controlMode: ControlMode = ControlMode.MANUAL,
)
