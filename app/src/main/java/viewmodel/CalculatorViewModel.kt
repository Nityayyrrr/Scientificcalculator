package com.example.scientificcalculator.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.*

class CalculatorViewModel : ViewModel() {

    var expression = mutableStateOf("")
        private set

    var result = mutableStateOf("0")
        private set

    var isDegree = mutableStateOf(true)
        private set

    val history = mutableStateListOf<String>()

    fun onInput(value: String) {
        when (value) {

            "C" -> {
                expression.value = ""
                result.value = "0"
            }

            "⌫" -> {
                if (expression.value.isNotEmpty()) {
                    expression.value = expression.value.dropLast(1)
                }
            }

            "=" -> calculate()

            "DEG/RAD" -> {
                isDegree.value = !isDegree.value
            }

            "sin", "cos", "tan", "√" -> {
                expression.value += "$value("
            }

            else -> {
                expression.value += value
            }
        }
    }

    private fun calculate() {
        try {
            var exp = expression.value
                .replace("×", "*")
                .replace("÷", "/")
                .replace("√", "sqrt")

            if (isDegree.value) {
                exp = exp
                    .replace("sin(", "sin(pi/180*")
                    .replace("cos(", "cos(pi/180*")
                    .replace("tan(", "tan(pi/180*")
            }

            val value = ExpressionBuilder(exp).build().evaluate()

            val finalResult =
                if (value % 1 == 0.0) value.toInt().toString()
                else value.toString()

            result.value = finalResult
            history.add("${expression.value} = $finalResult")

        } catch (e: Exception) {
            result.value = "Error"
        }
    }

    fun clearHistory() {
        history.clear()
    }

    fun reuseHistory(item: String) {
        expression.value = item.substringBefore("=")
        result.value = item.substringAfter("=").trim()
    }
}
