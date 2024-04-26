package com.example.countercompoundview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton

class Counter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var corner:Int = 0

    init{
        LayoutInflater.from(context).inflate(R.layout.counter_compound_view, this, true)
    }

    private fun setButtonListener(){
        findViewById<MaterialButton>(R.id.tapButton).setOnClickListener {

        }
    }
}