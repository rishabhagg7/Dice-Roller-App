package com.example.dicerollerjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dicerollerjc.screens.HomeScreen
import com.example.dicerollerjc.ui.theme.DiceRollerJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerJCTheme {
                HomeScreen()
            }
        }
    }
}

