package com.example.countercompoundview

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.os.Parcelable
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
            if (!updateCounterDialogFragment.isAdded){
                updateCounterDialogFragment.show((context as MainActivity).supportFragmentManager, CONST.UPDATE_COUNTER);
            }
        }
    }

    private fun addCorner(extra:Int){
        corner+=extra

        updateView()
    }

    private fun updateView(){
        Log.d("TAG","updateView")
        findViewById<TextView>(R.id.corner).text = corner.toString()

        val paintDrawable = PaintDrawable(ContextCompat.getColor(context, R.color.yellow)).apply {
            setCornerRadius(corner.toFloat())
        }
        findViewById<View>(R.id.square).background = paintDrawable
    }

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putInt(CONST.CORNER,corner)
            putParcelable(CONST.SUPER_STATE,super.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if(state is Bundle){
            corner = state.getInt(CONST.CORNER)
            super.onRestoreInstanceState(state.getParcelable(CONST.SUPER_STATE))
        } else{
            super.onRestoreInstanceState(state)
        }
        updateView()
    }
}

object CONST{
    const val EXTRA = 10
    const val UPDATE_COUNTER = "UpdateCounterDialogFragment"
    const val CORNER = "Corner"
    const val SUPER_STATE = "SuperState"
}