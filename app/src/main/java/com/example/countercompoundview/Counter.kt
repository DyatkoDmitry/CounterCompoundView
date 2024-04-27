package com.example.countercompoundview

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class Counter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var corner:Int = 0

    private val onClickTap: () -> Unit = {
        addCorner(CONST.EXTRA)
    }

    private val updateCounterDialogFragment = UpdateCounterDialogFragment(onClickTap)

    init{
        LayoutInflater.from(context).inflate(R.layout.counter_compound_view, this, true)
        setButtonListener()
    }

    private fun setButtonListener(){
        findViewById<MaterialButton>(R.id.tapButton).setOnClickListener {
            updateCounterDialogFragment.show((context as MainActivity).supportFragmentManager, CONST.UPDATE_COUNTER);
        }
    }

    private fun addCorner(extra:Int){
        corner+=extra
        findViewById<TextView>(R.id.corner).text = corner.toString()

        val paintDrawable = PaintDrawable(ContextCompat.getColor(context, R.color.yellow))
        paintDrawable.setCornerRadius(corner.toFloat())
        findViewById<View>(R.id.square).background = paintDrawable
    }
}

object CONST{
    const val EXTRA = 10
    const val UPDATE_COUNTER = "UpdateCounterDialogFragment"
}