package com.bigosvaap.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val texto = findViewById<TextView>(R.id.texto)

        val calendar = Calendar.getInstance()


        val newFragment = DatePickerFragment(2000, 2030, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), onOk = {month, year ->
            texto.text = "$month - $year"
        })

        texto.setOnClickListener {
            newFragment.show(supportFragmentManager, "datePicker")
        }


    }
}