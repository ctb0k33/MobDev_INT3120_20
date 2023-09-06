package com.example.week2mobdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberPicker: NumberPicker = findViewById(R.id.NumberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1000);
        numberPicker.setValue(999);
        val totalMoney:TextView = findViewById(R.id.Total);
        val donateButton: Button = findViewById(R.id.Donate)
        donateButton.setOnClickListener(View.OnClickListener {
            var currentTextInput: TextInputEditText = findViewById(R.id.MoneyInput);
            var currentTextInputValue = currentTextInput.text.toString()
            var currentTextInputValueNumber =
                if (currentTextInputValue == "") 0 else currentTextInputValue.toInt()
            val newTotalMoney = totalMoney.text.toString().toInt() + currentTextInputValueNumber
            totalMoney.setText(newTotalMoney.toString())
        })
    }
}