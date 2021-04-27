package com.example.diploma_2020.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import androidx.core.content.ContextCompat.startActivity
import com.example.diploma_2020.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ReserveDialog(context: Context): Dialog(context) {
    lateinit var reserve: Button
    lateinit var name: TextInputEditText
    lateinit var number: TextInputEditText
    lateinit var dateText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_reserve)

        reserve = findViewById<Button>(R.id.reserve)
        name = findViewById<TextInputEditText>(R.id.nameText)
        number = findViewById<TextInputEditText>(R.id.numberOfPeopleText)
        val dateClick = findViewById<View>(R.id.dateClick)
        dateText = findViewById<TextInputEditText>(R.id.dateText)

        dateClick.setOnClickListener {
            val calendar = Calendar.getInstance()
            val calendarDialog = DatePickerDialog(
                context,
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    val day: String =
                        if (dayOfMonth.toString().length < 2) "0$dayOfMonth" else dayOfMonth.toString()
                    val monthStr: String =
                        if (month.toString().length < 2) "0" + (month + 1) else (month + 1).toString()
                    dateText.setText("$day/$monthStr/$year")
                },
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            )
            calendarDialog.show()
            calendarDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(context.getColor(R.color.dialog_blue))
            calendarDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(context.getColor(R.color.dialog_blue))
        }

    }
}