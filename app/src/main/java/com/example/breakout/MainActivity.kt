package com.example.breakout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import com.example.breakout.ui.GameUiState
import com.example.breakout.ui.theme.BreakoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BreakoutTheme {
                BreakoutApp()
            }
        }
    }
}
