package com.adsulganesh.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var isNewNumber = false
    var number1 = ""
    var op =""
    var isdot = false
    var operations:String = ""

    fun buNumberEvent(view: View) {

        if (isNewNumber)
            etNumber.setText("")
        isNewNumber = false
        val buSelect = view as Button
        var buClickValue:String = etNumber.text.toString()
        when(buSelect.id) {
            bu0.id -> buClickValue += "0"
            bu1.id -> buClickValue += "1"
            bu2.id -> buClickValue += "2"
            bu3.id -> buClickValue += "3"
            bu4.id -> buClickValue += "4"
            bu5.id -> buClickValue += "5"
            bu6.id -> buClickValue += "6"
            bu7.id -> buClickValue += "7"
            bu8.id -> buClickValue += "8"
            bu9.id -> buClickValue += "9"
            buDot.id ->{
                if (!isdot) {
                    buClickValue += "."
                    isdot = true
                }
            }
        }
        etNumber.setText(buClickValue)

    }


    fun buOperationEvent(view: View) {

        val buSelect  = view as Button
        operations = etNumber.text.toString()
        when(buSelect.id) {
            buSum.id -> op = "+"
            buSub.id -> op = "-"
            buMul.id -> op = "*"
            buDiv.id -> op = "/"
            buMod.id -> op = "%"
        }
        number1 = etNumber.text.toString()
        isNewNumber = true
        operations += op

        etOperation.setText(operations.toString())
    }

    fun buEqualEvent(view: View) {
        val number2 = etNumber.text.toString()
        operations += etNumber.text.toString()
        etOperation.setText(operations.toString())
        var result:Double?=null
        when(op){
            "+" -> result = number1.toDouble() + number2.toDouble()
            "-" -> result = number1.toDouble() - number2.toDouble()
            "*" -> result = number1.toDouble() * number2.toDouble()
            "/" -> result = number1.toDouble() / number2.toDouble()
            "%" -> result = (number1.toDouble() * number2.toDouble()) / 100
        }
        etNumber.setText(result.toString())
        op = ""
        isNewNumber = true
    }

    fun buACEvent(view: View) {

        isNewNumber = true
        op = ""
        number1 = ""
        etNumber.setText("")
        operations = ""
        etOperation.setText("")
    }

    fun buBackSpace(view: View) {
        var num:String = etNumber.text.toString()
        num = removeLastChar(num)
        etNumber.setText(num)
    }
    fun removeLastChar(s:String):String{
        if(s == "" || s.length == 0)
            return ""
        else if(s.last() == '.'){
            isdot = false
            return (s.substring(0, s.length - 1))
        }
        else
            return (s.substring(0, s.length - 1))
    }
}
