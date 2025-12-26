package com.example.scientificcalculator.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scientificcalculator.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {

    var showHistory by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            )
            .statusBarsPadding() // 🔥 prevents overlap without white bar
    ) {

        // ─── HISTORY DRAWER ───
        AnimatedVisibility(
            visible = showHistory,
            enter = slideInHorizontally { -it },
            exit = slideOutHorizontally { -it }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
                    .padding(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.15f)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Text(
                        text = "History",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )

                    TextButton(onClick = { viewModel.clearHistory() }) {
                        Text("Clear", color = Color.White)
                    }

                    LazyColumn {
                        items(viewModel.history.reversed()) { item ->
                            Text(
                                text = item,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        viewModel.reuseHistory(item)
                                        showHistory = false
                                    }
                            )
                        }
                    }
                }
            }
        }

        // ─── MAIN CALCULATOR PANEL ───
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(start = if (showHistory) 260.dp else 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.12f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                // ─── TOP INLINE BAR (NO APP TITLE) ───
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { showHistory = !showHistory }) {
                        Text("History", color = Color(0xFFB388FF))
                    }

                    Text(
                        text = if (viewModel.isDegree.value) "DEG" else "RAD",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                CalculatorDisplay(
                    expression = viewModel.expression.value,
                    result = viewModel.result.value
                )

                Spacer(modifier = Modifier.height(12.dp))

                CalculatorButtons(viewModel)
            }
        }
    }
}
