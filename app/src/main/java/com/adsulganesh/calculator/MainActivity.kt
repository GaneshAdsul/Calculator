package com.adsulganesh.calculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et:EditText = findViewById(R.id.etNumber)
        val et1:TextView= findViewById(R.id.etOperation)

        //
        if (Build.VERSION.SDK_INT >= 21) {
            et!!.showSoftInputOnFocus = false
            et1!!.showSoftInputOnFocus = false
        } else if (Build.VERSION.SDK_INT >= 11) {
            et!!.setRawInputType(InputType.TYPE_CLASS_TEXT)
            et!!.setTextIsSelectable(true)
            et1!!.setRawInputType(InputType.TYPE_CLASS_TEXT)
            et1!!.setTextIsSelectable(true)

        } else {
            et!!.setRawInputType(InputType.TYPE_NULL)
            et!!.isFocusable = true
            et1!!.setRawInputType(InputType.TYPE_NULL)
            et1!!.isFocusable = true

        }

    }

    var isNewNumber = false
    var number1 = ""        //value of number 1 is stored
    var op =""              //which operation is to be performed is
    var isdot = false       //to check if current number have dot
    var operations:String = ""      //to display the whole operation

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
        isdot = false
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
        etNumber.setText(removeDot(result.toString()))
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
        isdot = false
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


    fun removeDot(num:String):String {

        var a=num.toDouble()
        val df = DecimalFormat("#")

        if (isWhole(a))
            return df.format(a).toString()
        else
            return  a.toString()
    }
    fun isWhole(value: Double):Boolean {
        return value - value.toInt() == 0.0
    }

    fun buNon(view: View) {}
}