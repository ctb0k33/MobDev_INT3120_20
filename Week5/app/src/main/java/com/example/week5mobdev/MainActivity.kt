package com.example.week5mobdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import android.app.Activity





class MainActivity : AppCompatActivity() {
    val fmtDateAndTime = DateFormat.getDateTimeInstance()
    lateinit var lblDateAndTime: TextView
    val myCalendar = Calendar.getInstance()

    val d = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    val t = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        myCalendar.set(Calendar.MINUTE, minute)
        updateLabel()
    }
    fun updateLabel() {
        lblDateAndTime.text = fmtDateAndTime.format(myCalendar.time)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblDateAndTime = findViewById(R.id.lblDateAndTime)

        // Initialize the TextView with an initial date and time
        lblDateAndTime.text = fmtDateAndTime.format(myCalendar.time)

        // Set up click listeners for date and time pickers
        lblDateAndTime.setOnClickListener {
            DatePickerDialog(
                this,
                d,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        lblDateAndTime.setOnLongClickListener {
            TimePickerDialog(
                this,
                t,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                true
            ).show()
            true
        }
    }
}