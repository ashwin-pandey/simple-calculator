package com.example.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View)
    {
        if(view is Button)
        {
            if (view.text == ".")
            {
                if(canAddDecimal)
                    resultsTV.append(view.text)
                canAddDecimal = false
            }
            else
                resultsTV.append(view.text)

            canAddOperation = true
        }
    }

    fun operationAction(view: View)
    {
        if(view is Button && canAddOperation)
        {
            resultsTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun backSpaceAction(view: View)
    {
        val length = resultsTV.length()
        if (length > 0)
            resultsTV.text = resultsTV.text.subSequence(0, length - 1)
    }

    fun allClearAction(view: View)
    {
        workingsTV.text = ""
        resultsTV.text = ""
    }

    fun equalsAction(view: View)
    {
        val text = resultsTV.text.toString()
        workingsTV.text = text
        try
        {
            val expression = ExpressionBuilder(text).build()
            val result = expression.evaluate()
            resultsTV.text = result.toString()
        }
        catch(ex: ArithmeticException)
        {
            resultsTV.text = ex.message
        }
    }
}