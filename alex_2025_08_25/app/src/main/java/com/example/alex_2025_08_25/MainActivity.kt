package com.example.alex_2025_08_25

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainColumn = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#FFFFFF"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setPadding(16, 16, 16, 16)
        }

        val display = TextView(this).apply {
            text = "0"
            setTextColor(Color.BLACK)
            textSize = 72f
            gravity = Gravity.END
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        mainColumn.addView(display)

        val buttonContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }
        mainColumn.addView(buttonContainer)

        fun createButton(text: String): Button {
            return Button(this).apply {
                this.text = text
                textSize = 28f
                setTextColor(Color.WHITE)
                setBackgroundColor(Color.parseColor("#00008B"))
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply {
                    marginEnd = 8
                    marginStart = 8
                }
            }
        }

        fun createRow(vararg buttons: Button): LinearLayout {
            return LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f).apply {
                    bottomMargin = 16
                }
                buttons.forEach { addView(it) }
            }
        }

        buttonContainer.addView(createRow(createButton("1"), createButton("2"), createButton("3"), createButton("+")))
        buttonContainer.addView(createRow(createButton("4"), createButton("5"), createButton("6"), createButton("-")))
        buttonContainer.addView(createRow(createButton("7"), createButton("8"), createButton("9"), createButton("*")))
        buttonContainer.addView(createRow(createButton("C"), createButton("0"), createButton("="), createButton("/")))

        setContentView(mainColumn)
    }
}