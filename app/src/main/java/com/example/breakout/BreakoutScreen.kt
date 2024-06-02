package com.example.breakout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.breakout.ui.GameViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breakout.ui.MenuScreen
import com.example.breakout.ui.PlayScreen
import com.example.breakout.ui.SettingsScreen

enum class BreakoutScreen {
    Menu,
    Play,
    Settings,
}

@Composable
fun BreakoutApp (
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BreakoutScreen.valueOf(
        backStackEntry?.destination?.route ?: BreakoutScreen.Menu.name
    )

    NavHost(navController = navController, startDestination = BreakoutScreen.Menu.name) {
        composable(route = BreakoutScreen.Menu.name) {
            MenuScreen(navController = navController)
        }
        composable(route = BreakoutScreen.Play.name) {
            PlayScreen(navController = navController)
        }
        composable(route = BreakoutScreen.Settings.name) {
            SettingsScreen(navController = navController)
        }
    }

}