package com.example.inchestocm

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textView: TextView
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById(R.id.editTextText)
        buttonConvert = findViewById(R.id.buttonConvert)
        textView = findViewById(R.id.resultText)
        radioGroup = findViewById(R.id.radioGroup)


        editText.addTextChangedListener {
            buttonConvert.isEnabled = it.toString().toDoubleOrNull() != null
        }

        buttonConvert.setOnClickListener {
            val inchesValue = editText.text.toString().toDoubleOrNull()
            textView.visibility = TextView.VISIBLE
            if (inchesValue == null) {
                textView.text = "Please Enter a valid number"
                return@setOnClickListener
            }
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                textView.text = "Please select a conversion type"
                return@setOnClickListener
            }

            val result = when (selectedRadioButtonId) {
                R.id.radioToCM -> "${"%.2f".format(inchesValue * 2.54)} cm"
                R.id.radioToFeet -> "${"%.2f".format(inchesValue / 12)} ft"
                R.id.radioToMeter -> "${"%.2f".format(inchesValue * 0.0254)} m"
                else -> {
                    textView.text = "Please select a conversion type"
                    return@setOnClickListener
                }
            }

            val displayInches = if (inchesValue % 1.0 == 0.0) {
                inchesValue.toInt().toString()
            } else {
                inchesValue
            }
            textView.text = "$displayInches inches is $result"
        }


    }
}



