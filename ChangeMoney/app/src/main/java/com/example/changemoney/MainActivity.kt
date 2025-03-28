package com.example.changemoney

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputSnipper: Spinner = findViewById<Spinner>(R.id.fromThis)
        var outputSnipper = findViewById<Spinner>(R.id.toThis)
        var change = findViewById<TextView>(R.id.change)
        val inputMoney = findViewById<TextView>(R.id.inputMoney)
        val outputMoney = findViewById<TextView>(R.id.outputMoney)
        val buttonChange = findViewById<Button>(R.id.btn_change)
        val inputIcon = findViewById<TextView>(R.id.icon_input)
        val outputIcon = findViewById<TextView>(R.id.icon_output)

        val options = listOf("United States - Dollar", "Euro - Euro", "Japan - Yen", "United Kingdom - Pound", "VietNam - Dong")
        val toVietNamDong = listOf(25574.99, 27588.0, 169.47, 33184.76, 1.0)
        val icons = listOf("$", "€", "¥", "£", "₫")
        var firstMoney = 0.0
        var secondMoney = 0.0
        var indexInputMoney = 0
        var indexOutputMoney = 0
        var fromM = ""
        var toM = ""
        var scaleMoney = ""

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        inputSnipper.adapter = adapter
        outputSnipper.adapter = adapter

        inputSnipper.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when(selectedItem){
                    "United States - Dollar" -> {
                        indexInputMoney = 0
                        fromM = "USD"
                    }
                    "Euro - Euro" -> {
                        indexInputMoney = 1
                        fromM = "EUR"
                    }
                    "Japan - Yen" -> {
                        indexInputMoney = 2
                        fromM = "JPY"
                    }
                    "United Kingdom - Pound" -> {
                        indexInputMoney = 3
                        fromM = "GBP"
                    }
                    "VietNam - Dong" -> {
                        indexInputMoney = 4
                        fromM = "VND"
                    }
                }
                inputIcon.text = icons[indexInputMoney]
                scaleMoney = String.format("%.5f", toVietNamDong[indexInputMoney] / toVietNamDong[indexOutputMoney])
                change.text = "1.0 " + fromM + " = " + scaleMoney + " " + toM
                outputMoney.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        outputSnipper.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when(selectedItem){
                    "United States - Dollar" -> {
                        indexOutputMoney = 0
                        toM = "USD"
                    }
                    "Euro - Euro" -> {
                        indexOutputMoney = 1
                        toM = "EUR"
                    }
                    "Japan - Yen" -> {
                        indexOutputMoney = 2
                        toM = "JPY"
                    }
                    "United Kingdom - Pound" -> {
                        indexOutputMoney = 3
                        toM = "GBP"
                    }
                    "VietNam - Dong" -> {
                        indexOutputMoney = 4
                        toM = "VND"
                    }
                }
                outputIcon.text = icons[indexOutputMoney]
                scaleMoney = String.format("%.5f", toVietNamDong[indexInputMoney] / toVietNamDong[indexOutputMoney])
                change.text = "1.0 " + fromM + " = " + scaleMoney + " " + toM
                outputMoney.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        buttonChange.setOnClickListener(){
            firstMoney = inputMoney.text.toString().toDoubleOrNull() ?: 0.0
            secondMoney = firstMoney * toVietNamDong[indexInputMoney] / toVietNamDong[indexOutputMoney]
            outputMoney.text = secondMoney.toString()
        }

    }
}