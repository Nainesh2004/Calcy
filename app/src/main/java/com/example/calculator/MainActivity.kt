package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginLeft
import kotlin.math.*


class MainActivity : AppCompatActivity() {


    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var equals: Button
    lateinit var sin: Button
    lateinit var cos: Button
    lateinit var tan: Button
    lateinit var power: Button
    lateinit var pi: Button
    lateinit var bstart: Button
    lateinit var bclose: Button
    lateinit var nfact: Button
    lateinit var log: Button
    lateinit var sqrt:Button
    private lateinit var myImageButton: ImageButton
    private lateinit var myImageButton1: ImageButton


    var currentInput: String = ""
    var currentOperator: String? = null
    var firstOperand: Double = 0.0
    var isResultDisplayed: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv2 = findViewById(R.id.tv2)
        tv1 = findViewById(R.id.tv1)

        val buttons = arrayOf(
            R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven,
            R.id.eight, R.id.nine, R.id.double_zero, R.id.zero, R.id.dot,
        )


        for (buttonID in buttons) {
            findViewById<Button>(buttonID).setOnClickListener { onDigitClicked(it) }
        }
        findViewById<Button>(R.id.pluse).setOnClickListener { onOperatorClicked("+") }
        findViewById<Button>(R.id.minus).setOnClickListener { onOperatorClicked("-") }
        findViewById<Button>(R.id.multi).setOnClickListener { onOperatorClicked("*") }
        findViewById<Button>(R.id.div).setOnClickListener { onOperatorClicked("/") }
        findViewById<Button>(R.id.modulo).setOnClickListener { onOperatorClicked("%") }
//        findViewById<Button>(R.id.sin).setOnClickListener { onOperatorClicked("sin") }
//        findViewById<Button>(R.id.sqrt).setOnClickListener { onOperatorClicked("sqrt") }

        findViewById<Button>(R.id.clear).setOnClickListener { backspace() }
        findViewById<Button>(R.id.ac).setOnClickListener { ClearAll() }
        findViewById<Button>(R.id.equals).setOnClickListener { calculateResult() }
//        findViewById<Button>(R.id.sin)

        sin = findViewById(R.id.sin)
        sin.setOnClickListener {
            Toast.makeText(this@MainActivity, "Sin is clicked", Toast.LENGTH_SHORT).show()
        }
        cos = findViewById(R.id.cos)
        cos.setOnClickListener {
            Toast.makeText(this@MainActivity, "Cos is clicked", Toast.LENGTH_SHORT).show()
        }
        tan = findViewById(R.id.tan)
        tan.setOnClickListener {
            Toast.makeText(this@MainActivity, "tan is clicked", Toast.LENGTH_SHORT).show()
        }
        power = findViewById(R.id.power)
        power.setOnClickListener {
            Toast.makeText(this@MainActivity, "power is clicked", Toast.LENGTH_SHORT).show()
        }
        pi = findViewById(R.id.pi)
        pi.setOnClickListener {
            Toast.makeText(this@MainActivity, "pi is clicked", Toast.LENGTH_SHORT).show()
        }
        bstart = findViewById(R.id.bstart)
        bstart.setOnClickListener {
            Toast.makeText(this@MainActivity, "Bracket Start is clicked", Toast.LENGTH_SHORT).show()
        }
        bclose = findViewById(R.id.bclose)
        bclose.setOnClickListener {
            Toast.makeText(this@MainActivity, "Bracket close is clicked", Toast.LENGTH_SHORT).show()
        }
        nfact = findViewById(R.id.n)
        nfact.setOnClickListener {
            Toast.makeText(this@MainActivity, "n! is clicked", Toast.LENGTH_SHORT).show()
        }
        log = findViewById(R.id.log)
        log.setOnClickListener {
            Toast.makeText(this@MainActivity, "log is clicked", Toast.LENGTH_SHORT).show()
        }
        sqrt = findViewById( R.id.sqrt)
//        equals = findViewById(R.id.equals)
        sqrt.setOnClickListener {
            Toast.makeText(this@MainActivity, "Sqrt is clicked", Toast.LENGTH_SHORT).show()
        }
        myImageButton = findViewById(R.id.igbtn)
        myImageButton1 = findViewById(R.id.igbtn1)
    }
    fun onDigitClicked(view: View){
        if(isResultDisplayed){
            ClearAll()
            isResultDisplayed = false
        }
        currentInput += (view as Button).text.toString()
        updateDisplay()
    }


    fun onOperatorClicked(operator: String){
            if (currentInput.isNotEmpty()) {
                if (currentOperator != null) {
                    calculateResult()
                }
                currentOperator = operator
                firstOperand = currentInput.toDouble()
                currentInput = ""
                isResultDisplayed = false
                updateDisplay()
            } else if (isResultDisplayed) {
                currentOperator = operator
                currentInput = firstOperand.toString()
                isResultDisplayed = false
                updateDisplay()
            }
    }

    fun ClearAll(){ //When Clicked on AC- All Clear
        currentInput = ""
        currentOperator = null
        firstOperand = 0.0
        isResultDisplayed = false
        updateDisplay()
    }
    fun backspace(){
        if(currentInput.isNotEmpty()){
            currentInput = currentInput.substring(0,currentInput.length-1)
            updateDisplay()
        }
    }

    fun onImageButtonClick(view: View) {
        // Show the hidden button
        sin.visibility = View.VISIBLE
        cos.visibility = View.VISIBLE
        tan.visibility = View.VISIBLE
        power.visibility = View.VISIBLE
        pi.visibility = View.VISIBLE
        bstart.visibility = View.VISIBLE
        bclose.visibility = View.VISIBLE
        nfact.visibility = View.VISIBLE
        log.visibility = View.VISIBLE
        sqrt.visibility=View.VISIBLE
        myImageButton.visibility = View.GONE
        myImageButton1.visibility=View.VISIBLE
    }
    fun onImageButtonClick1(view: View) {
        //  hide the button
        sin.visibility = View.GONE
        cos.visibility = View.GONE
        tan.visibility = View.GONE
        power.visibility = View.GONE
        pi.visibility = View.GONE
        bstart.visibility = View.GONE
        bclose.visibility = View.GONE
        nfact.visibility = View.GONE
        log.visibility = View.GONE
        sqrt.visibility=View.GONE
        myImageButton.visibility = View.VISIBLE
        myImageButton1.visibility=View.GONE
    }

    fun calculateResult() {
        if (currentOperator != null && currentInput.isNotEmpty()) {
            val secondOperand = currentInput.toDouble()
            when (currentOperator) {
                "+" -> firstOperand += secondOperand
                "-" -> firstOperand -= secondOperand
                "*" -> firstOperand *= secondOperand
                "/" -> {
                    if (secondOperand != 0.0) {
                        firstOperand /= secondOperand
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Division By Zero Error",
                            Toast.LENGTH_SHORT
                        ).show()
                        ClearAll()
                        tv1.text = "Error"
                        return
                    }
                }
                "%" -> firstOperand = (firstOperand * secondOperand) / 100
                "sin" -> {
                    firstOperand = sin(Math.toRadians(secondOperand))
                }
            }
            currentOperator = null
            currentInput = firstOperand.toString()
            isResultDisplayed = true
            updateDisplay()
            tv1.text = "=$firstOperand" // here tv1
        }
    }


    fun updateDisplay() {
        val expression = if(currentOperator != null){
            "$firstOperand $currentOperator $currentInput"
        }else{
            currentInput
        }
        tv2.text = expression
        tv1.text = if(currentOperator != null){ //here tv1
            "$firstOperand $currentOperator"
        }else{
            ""
        }
    }
}

//
//"sqrt" -> {
//    if (firstOperand >= 0.0) {
//        firstOperand = sqrt(firstOperand)
//    } else {
//        Toast.makeText(
//            this@MainActivity,
//            "Invalid Input for Square Root",
//            Toast.LENGTH_SHORT
//        ).show()
//        ClearAll()
//        tv1.text = "Error"
//        return
//    }
//}