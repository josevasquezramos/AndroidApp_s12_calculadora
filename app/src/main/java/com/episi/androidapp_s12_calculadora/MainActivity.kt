package com.episi.androidapp_s12_calculadora

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.commit
import com.episi.androidapp_s12_calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.expressionEditText.disableKeyboard()
        setContentView(binding.root)

        val isLightTheme = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK !=
                android.content.res.Configuration.UI_MODE_NIGHT_YES

        if (isLightTheme) {
            window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = true
                }
                view.onApplyWindowInsets(insets)
            }
        }

        setupObservers()
        setupButtons()
        setupEditText()

        onBackPressedDispatcher.addCallback(this) {
            val fm = supportFragmentManager
            if (fm.backStackEntryCount > 0) {
                binding.fragmentContainer.visibility = View.GONE // 👈 Esto es clave
                fm.popBackStack()
            } else {
                finish()
            }
        }
    }

    private fun EditText.disableKeyboard() {
        showSoftInputOnFocus = false
        setRawInputType(android.text.InputType.TYPE_CLASS_TEXT)
        isCursorVisible = true
    }

    private fun setupObservers() {
        viewModel.expression.observe(this) { expr ->
            if (binding.expressionEditText.text.toString() != expr) {
                binding.expressionEditText.setText(expr)
                binding.expressionEditText.setSelection(expr.length)
            }
        }

        viewModel.result.observe(this) { res ->
            binding.resultTextView.text = res
        }
    }

    private fun setupEditText() {
        binding.expressionEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No acción directa aquí para evitar bucle con LiveData
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && binding.expressionEditText.hasFocus()) {
                    viewModel.updateExpression(s.toString())
                }
            }
        })

        binding.expressionEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.evaluateCurrentExpression()
                true
            } else false
        }
    }

    private fun setupButtons() {
        val buttonMap = mapOf(
            binding.btn0 to "0", binding.btn1 to "1", binding.btn2 to "2",
            binding.btn3 to "3", binding.btn4 to "4", binding.btn5 to "5",
            binding.btn6 to "6", binding.btn7 to "7", binding.btn8 to "8", binding.btn9 to "9",
            binding.btnPlus to "+", binding.btnMinus to "-", binding.btnMultiply to "*",
            binding.btnDivide to "/", binding.btnDot to ".", binding.btnPercent to "^",
        )

        buttonMap.forEach { (button, value) ->
            button.setOnClickListener {
                val cursor = binding.expressionEditText.selectionStart
                viewModel.appendToExpression(value, cursor)
            }
        }

        binding.btnParenthesis.setOnClickListener {
            val cursor = binding.expressionEditText.selectionStart
            viewModel.insertSmartParenthesis(cursor)
        }

        binding.btnClear.setOnClickListener {
            viewModel.clearExpression()
        }

        binding.btnDelete.setOnClickListener {
            val cursor = binding.expressionEditText.selectionStart
            viewModel.deleteLast(cursor)
        }

        binding.btnEquals.setOnClickListener {
            viewModel.evaluateCurrentExpression()
        }

        binding.historyButton.setOnClickListener {
            binding.fragmentContainer.visibility = View.VISIBLE
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("history")
                replace(R.id.fragmentContainer, HistoryFragment())
            }
        }
    }
}
