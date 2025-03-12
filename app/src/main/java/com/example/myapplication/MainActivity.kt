package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    var outputTxt = "0"
    var outputCalc = ""
    val arr = mutableListOf<String>()
    val stackChar = Stack<Char>()


    fun levelOf(c: Char): Int {
        return when (c) {
            '+', '-' -> 4
            'x', '/' -> 5
            else -> 1
        }
    }

    fun addStackAndList(c : Char, num : Int) : Unit {
        arr.add(num.toString())
        while(!stackChar.empty() && levelOf(stackChar.peek()) >= levelOf(c)){
            arr.add(stackChar.pop().toString())
        }
        if(c != '#') {
            stackChar.push(c)
        }
    }

    fun calcResult(num1 : Int, c : Char, num2 : Int) : Int {
        return when(c){
            '+' -> num1 + num2
            '-' -> num1 - num2
            'x' -> num1 * num2
            else -> num1 / num2
        }
    }

    fun calcTheNumber(): String {
        var result = 0
        val stackInt = Stack<Int>()
        for(str in arr){
            if(str[0].isDigit() || str.length > 1 && str[1].isDigit()){
                stackInt.push(str.toInt())
            } else {
                if(stackInt.empty()){
                    arr.clear()
                    return "ERR"
                }
                val num2 = stackInt.pop()
                if(str[0] == '/' && num2 == 0){
                    while(!stackInt.empty()){
                        stackInt.pop()
                    }
                    arr.clear()
                    return "ERR"
                }
                if(stackInt.empty()){
                    arr.clear()
                    return "ERR"
                }
                val num1 = stackInt.pop()
                stackInt.push(calcResult(num1, str[0], num2))
            }
        }
        arr.clear()
        result = stackInt.pop()
        return result.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputTxt = findViewById<TextView>(R.id.txt_input)
        val calcTxt = findViewById<TextView>(R.id.txt_calc)
        inputTxt.text = "0"
        inputTxt.textDirection = View.TEXT_DIRECTION_LTR
        inputTxt.gravity = Gravity.END
        calcTxt.textDirection = View.TEXT_DIRECTION_LTR
        calcTxt.gravity = Gravity.END

        findViewById<Button>(R.id.btn_1).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "1"
            } else {
                outputTxt = "1"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_2).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "2"
            } else {
                outputTxt = "2"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_3).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "3"
            } else {
                outputTxt = "3"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_4).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "4"
            } else {
                outputTxt = "4"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_5).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "5"
            } else {
                outputTxt = "5"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_6).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "6"
            } else {
                outputTxt = "6"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_7).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "7"
            } else {
                outputTxt = "7"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_8).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "8"
            } else {
                outputTxt = "8"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_9).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "9"
            } else {
                outputTxt = "9"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_0).setOnClickListener{
            if(outputTxt != "0") {
                outputTxt += "0"
            } else {
                outputTxt = "0"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_add).setOnClickListener{
            if(outputTxt != "") {
                outputCalc += outputTxt + "+"
                addStackAndList('+', outputTxt.toInt())
                outputTxt = ""
                calcTxt.text = outputCalc
                inputTxt.text = ""
            }
        }
        findViewById<Button>(R.id.btn_sub).setOnClickListener{
            if(outputTxt != "")  {
                outputCalc += outputTxt + "-"
                addStackAndList('-', outputTxt.toInt())
                outputTxt = ""
                calcTxt.text = outputCalc
                inputTxt.text = ""
            }
        }
        findViewById<Button>(R.id.btn_mul).setOnClickListener{
            if(outputTxt != "")  {
                outputCalc += outputTxt + "x"
                addStackAndList('x', outputTxt.toInt())
                outputTxt = ""
                calcTxt.text = outputCalc
                inputTxt.text = ""
            }
        }
        findViewById<Button>(R.id.btn_div).setOnClickListener{
            if(outputTxt != "")  {
                outputCalc += outputTxt + "/"
                addStackAndList('/', outputTxt.toInt())
                outputTxt = ""
                calcTxt.text = outputCalc
                inputTxt.text = ""
            }
        }
        findViewById<Button>(R.id.btn_back).setOnClickListener{
            outputTxt = outputTxt.dropLast(1)
            if(outputTxt == ""){
                outputTxt = "0"
            }
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_CE).setOnClickListener{
            outputTxt = "0"
            inputTxt.text = "0"
        }
        findViewById<Button>(R.id.btn_C).setOnClickListener{
            outputTxt = "0"
            outputCalc = ""
            calcTxt.text = ""
            inputTxt.text = "0"
        }
        findViewById<Button>(R.id.btn_reverse).setOnClickListener{
            val x = inputTxt.text.toString().toIntOrNull() ?: 0
            outputTxt = (-x).toString()
            inputTxt.text = outputTxt
        }
        findViewById<Button>(R.id.btn_equal).setOnClickListener{
            if(outputTxt == ""){
                inputTxt.text = "ERR"
                outputTxt = "0"
            } else {
                outputCalc += outputTxt
                calcTxt.text = outputCalc
                // infix to postfix
                addStackAndList('#', outputTxt.toInt())
                // postfix to value
                outputTxt = calcTheNumber()
                inputTxt.text = outputTxt
                if(outputTxt == "ERR"){
                    outputTxt = "0"
                }
            }
            outputCalc = ""
        }
    }



}