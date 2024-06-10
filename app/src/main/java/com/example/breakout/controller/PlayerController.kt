package com.example.breakout.controller

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.breakout.Player
import com.example.breakout.ui.GameUiState


enum class ControlMode {
    MANUAL, GYROSCOPE
}

class PlayerController(
    internal val player: Player,
    private val gameUiState: GameUiState,
    context: Context
) {
    private var currentGyroscopeData: Float = 0f

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    var playerXPosition by mutableStateOf(0f)
    var leftCornerXPosition by mutableStateOf(0f)
    var rightCornerXPosition by mutableStateOf(0f)

    private val gyroscopeListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (gameUiState.controlMode == ControlMode.GYROSCOPE) {
                val speedMultiplier = gameUiState.playerMovementSpeedGyroscop
                // Aktualizacja danych z żyroskopu
                currentGyroscopeData = event.values[0]
                playerXPosition += currentGyroscopeData * speedMultiplier
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Nie potrzebujemy tego zaimplementować
        }
    }

    init {
        sensorManager.registerListener(gyroscopeListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    // Wyrejestrowanie słuchacza żyroskopu w momencie zakończenia działania kontrolera
    fun dispose() {
        sensorManager.unregisterListener(gyroscopeListener)
    }

    fun updatePlayerPositionWithGyroscope(
        gyroscopeData: Float,
        leftBoundary: Float,
        rightBoundary: Float
    ) {
        Log.d("PlayerController", "siema siema siema")
        Log.d("PlayerController", "Gyroscope data: $gyroscopeData")
        Log.d("PlayerController", "Gyroscope data: ${gameUiState.playerMovementSpeed}")
            val speedMultiplier = gameUiState.playerMovementSpeedGyroscop
        val newPosition = playerXPosition + (gyroscopeData * speedMultiplier)
        playerXPosition = newPosition.coerceIn(leftBoundary, rightBoundary)

    }


    fun getCurrentGyroscopeData(): Float {
        return currentGyroscopeData
    }
}
