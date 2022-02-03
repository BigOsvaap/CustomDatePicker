package com.bigosvaap.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class DatePickerFragment(
    private val minYear: Int,
    private val maxYear: Int,
    private val monthSelected: Int?,
    private val yearSelected: Int?,
    private val onOk: (month: String, year: String) -> Unit
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        if (monthSelected !in 0..11) throw Error("monthSelected must be a number between 1 and 12")
        if (yearSelected !in minYear..maxYear) throw Error("yearSelected must be a number between minYear and maxYear")

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val root = inflater.inflate(R.layout.dialog_month_year_picker, null)

            val pickerMonth = root.findViewById<NumberPicker>(R.id.month)
            pickerMonth.minValue = 0
            pickerMonth.maxValue = 11
            pickerMonth.value = monthSelected ?: pickerMonth.minValue
            pickerMonth.displayedValues = resources.getStringArray(R.array.months_year)

            val pickerYear = root.findViewById<NumberPicker>(R.id.year)
            pickerYear.minValue = minYear
            pickerYear.maxValue = maxYear
            pickerYear.value = yearSelected ?: pickerYear.minValue


            builder.setView(root)
                .setPositiveButton(
                    R.string.ok
                ) { _, _ ->
                    val month = pickerMonth.displayedValues[pickerMonth.value]
                    val year = pickerYear.value.toString()
                    onOk(month, year)
                }
                .setNegativeButton(
                    R.string.cancel
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
    }
}