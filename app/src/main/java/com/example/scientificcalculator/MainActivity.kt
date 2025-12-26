package com.example.scientificcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.scientificcalculator.ui.CalculatorScreen
import com.example.scientificcalculator.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Removes top white space safely
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val viewModel = CalculatorViewModel()

        setContent {
            CalculatorScreen(viewModel)
        }
    }
}
