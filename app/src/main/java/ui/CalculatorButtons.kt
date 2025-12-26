package com.example.scientificcalculator.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scientificcalculator.viewmodel.CalculatorViewModel

private val buttonSize = 72.dp
private val bigButtonWidth = buttonSize * 2 + 12.dp

@Composable
fun CalculatorButtons(viewModel: CalculatorViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // ─── SCIENTIFIC ROW 1 ───
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IOSButton("DEG", Color.DarkGray, 12f, Modifier.size(buttonSize)) {
                viewModel.onInput("DEG/RAD")
            }
            IOSButton("sin", Color.DarkGray, 13f, Modifier.size(buttonSize)) {
                viewModel.onInput("sin")
            }
            IOSButton("cos", Color.DarkGray, 13f, Modifier.size(buttonSize)) {
                viewModel.onInput("cos")
            }
            IOSButton("tan", Color.DarkGray, 13f, Modifier.size(buttonSize)) {
                viewModel.onInput("tan")
            }
        }

        // ─── SCIENTIFIC ROW 2 ───
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IOSButton("π", Color.DarkGray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput("π")
            }
            IOSButton("e", Color.DarkGray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput("e")
            }
            IOSButton("log", Color.DarkGray, 14f, Modifier.size(buttonSize)) {
                viewModel.onInput("log")
            }
            IOSButton("ln", Color.DarkGray, 14f, Modifier.size(buttonSize)) {
                viewModel.onInput("ln")
            }
        }

        // ─── BRACKETS + POWERS ───
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IOSButton("(", Color.DarkGray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput("(")
            }
            IOSButton(")", Color.DarkGray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput(")")
            }
            IOSButton("x²", Color.DarkGray, 14f, Modifier.size(buttonSize)) {
                viewModel.onInput("^2")
            }
            IOSButton("x³", Color.DarkGray, 14f, Modifier.size(buttonSize)) {
                viewModel.onInput("^3")
            }
        }

        numberRow("7", "8", "9", "÷", viewModel)
        numberRow("4", "5", "6", "×", viewModel)
        numberRow("1", "2", "3", "-", viewModel)

        // ─── ZERO & PLUS ───
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IOSButton(
                "0",
                Color.Gray,
                20f,
                Modifier.width(bigButtonWidth).height(buttonSize)
            ) { viewModel.onInput("0") }

            IOSButton(".", Color.Gray, 22f, Modifier.size(buttonSize)) {
                viewModel.onInput(".")
            }

            IOSButton("+", Color(0xFFFF9500), 24f, Modifier.size(buttonSize)) {
                viewModel.onInput("+")
            }
        }

        // ─── LAST ROW ───
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IOSButton("C", Color.Gray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput("C")
            }
            IOSButton("⌫", Color.Gray, 18f, Modifier.size(buttonSize)) {
                viewModel.onInput("⌫")
            }

            IOSButton(
                "=",
                Color(0xFFFF9500),
                26f,
                Modifier.width(bigButtonWidth).height(buttonSize)
            ) {
                viewModel.onInput("=")
            }
        }
    }
}

@Composable
private fun numberRow(
    a: String,
    b: String,
    c: String,
    d: String,
    viewModel: CalculatorViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IOSButton(a, Color.Gray, 20f, Modifier.size(buttonSize)) { viewModel.onInput(a) }
        IOSButton(b, Color.Gray, 20f, Modifier.size(buttonSize)) { viewModel.onInput(b) }
        IOSButton(c, Color.Gray, 20f, Modifier.size(buttonSize)) { viewModel.onInput(c) }
        IOSButton(d, Color(0xFFFF9500), 24f, Modifier.size(buttonSize)) { viewModel.onInput(d) }
    }
}

@Composable
fun IOSButton(
    text: String,
    color: Color,
    fontSize: Float,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(4.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )
    }
}
