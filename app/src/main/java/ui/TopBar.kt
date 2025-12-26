package com.example.scientificcalculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    onHistoryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 12.dp, end = 12.dp)
    ) {
        TextButton(
            onClick = onHistoryClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "History",
                color = Color(0xFFB388FF)
            )
        }
    }
}
