package com.episi.androidapp_s12_calculadora

import CalculatorRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel(private val repository: CalculatorRepository) : ViewModel() {

    val expression = MutableLiveData("")
    private val _result = MutableLiveData("")
    val result: LiveData<String> = _result

    private val _history = MutableLiveData<List<String>>(repository.getHistory())
    val history: LiveData<List<String>> = _history

    fun appendToExpression(symbol: String, cursorPosition: Int) {
        val current = expression.value ?: ""
        val new = current.substring(0, cursorPosition) + symbol + current.substring(cursorPosition)
        expression.value = new
        evaluateExpression(new)
    }

    fun updateExpression(newExpr: String) {
        expression.value = newExpr
        evaluateExpression(newExpr)
    }

    fun clearExpression() {
        expression.value = ""
        _result.value = ""
    }

    fun deleteLast(cursorPosition: Int) {
        val current = expression.value ?: return
        if (cursorPosition > 0) {
            val new = current.removeRange(cursorPosition - 1, cursorPosition)
            expression.value = new
            evaluateExpression(new)
        }
    }

    fun insertSmartParenthesis(cursorPosition: Int) {
        val current = expression.value ?: ""
        val openCount = current.take(cursorPosition).count { it == '(' }
        val closeCount = current.take(cursorPosition).count { it == ')' }

        val symbol = if (openCount > closeCount) ")" else "("
        appendToExpression(symbol, cursorPosition)
    }

    fun evaluateCurrentExpression() {
        val expr = expression.value ?: return
        try {
            val resultValue = ExpressionBuilder(expr).build().evaluate().toString()
            val fullOperation = "$expr = $resultValue"
            repository.saveOperation(fullOperation)
            _history.value = repository.getHistory()
            expression.value = resultValue // sobrescribe la expresión
            _result.value = ""
        } catch (e: Exception) {
            _result.value = "Error"
        }
    }

    private fun evaluateExpression(expr: String) {
        try {
            val resultValue = ExpressionBuilder(expr).build().evaluate().toString()
            _result.value = resultValue
        } catch (e: Exception) {
            _result.value = ""
        }
    }

    fun clearHistory() {
        repository.clearHistory()
        _history.value = emptyList()
    }

    fun loadHistory() {
        _history.value = repository.getHistory()
    }

    fun selectHistoryItem(operation: String) {
        val expressionPart = operation.substringBefore("=")
        updateExpression(expressionPart.trim())
    }
}