package com.example.breakout.controller

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class PlayerController(private val context: Context) : SensorEventListener {
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private var playerMovementSpeed: Float = 0f // Prędkość ruchu gracza

    init {
        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor == gyroscopeSensor) {
            val movementSpeed = event.values[0] // Odczytaj prędkość ruchu z żyroskopu

            // Aktualizacja prędkości ruchu gracza
            playerMovementSpeed = movementSpeed
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Nie potrzebujemy tego tutaj
    }

    // Metoda do zwracania prędkości ruchu gracza
    fun getPlayerMovementSpeed(): Float {
        return playerMovementSpeed
    }

    // Metoda do zatrzymania nasłuchiwania na zmiany w sensorze
    fun stop() {
        sensorManager.unregisterListener(this)
    }
}