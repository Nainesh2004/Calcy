package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tv1:TextView
    lateinit var tv2:TextView

    var currentInput:String=""
    var currentOperator:String?=null
    var firstOperand:Double=0.0
    var isResultDisplayed:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)

        val buttons = arrayOf(
            R.id.one, R.id.two, R.id.three,
            R.id.four, R.id.five, R.id.six,
            R.id.seven, R.id.eight, R.id.nine,
            R.id.double_zero, R.id.zero, R.id.dot
        )

        for (buttonId in buttons) {
//            findViewById<Button>(buttonId).setOnClickListener { onDigitClicked(it) }
        }
    }
}
//
//fun onDigitclicked(view: View){
//    currentInput += (view as Button).text.toString()
//}
//
//fun calculateResult(){}
//
//fun onOperatorClicked(operator:String){
//    if(currentInput.isNotEmpty()){
//    if(currentOperator != null){
//        calculateResult()
//    }
//    currentOperator=operator
//    firstOperand=currentInpur.toDouble()
//    currentInput=""
//    isResultDisplayed=false
//    updateDisplayed=false
//    updateDisplay()
//    }elseif(isResultDisplayed){
//        currentOperator=operator
//        currentInput=firstOperand.toString()
//        isResultDisplayed=false
//        updateDisplayed()
//    }
//}

