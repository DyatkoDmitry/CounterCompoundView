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
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.button.MaterialButton

class Counter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), DefaultLifecycleObserver {

    private var corner:Int = 0
    private var isWasReturn: Boolean = false
    private var startTime: Long? = null

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
        findViewById<TextView>(R.id.corner).text = corner.toString()

        val paintDrawable = PaintDrawable(ContextCompat.getColor(context, R.color.yellow)).apply {
            setCornerRadius(corner.toFloat())
        }
        findViewById<View>(R.id.square).background = paintDrawable
    }

    override fun onStop(owner: LifecycleOwner) {
        addCorner(CONST.TURN_OFF_EXTRA)

        if(!isWasReturn) isWasReturn = true

        startTime = System.currentTimeMillis()
    }

    override fun onResume(owner: LifecycleOwner) {
        if(isWasReturn){
            addCorner(CONST.RETURN_EXTRA)
        }
        minusCorner()
    }

    private fun minusCorner(){
        startTime?.let{
            val endTime = System.currentTimeMillis()
            val minutesInCreatedState = ((endTime - startTime!!) / 60000).toInt()
            val minusCorner = minutesInCreatedState * 2
            corner -= minusCorner
            updateView()
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putLong(CONST.START_TIME,startTime!!)
            putInt(CONST.CORNER,corner)
            putBoolean(CONST.IS_RETURN, isWasReturn)
            putParcelable(CONST.SUPER_STATE,super.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if(state is Bundle){
            startTime = state.getLong(CONST.START_TIME)
            corner = state.getInt(CONST.CORNER)
            isWasReturn = state.getBoolean(CONST.IS_RETURN)
            super.onRestoreInstanceState(state.getParcelable(CONST.SUPER_STATE))
        }else{
            super.onRestoreInstanceState(state)
        }
        updateView()
    }
}

object CONST{
    const val EXTRA = 10
    const val TURN_OFF_EXTRA = 5
    const val RETURN_EXTRA = 2
    const val UPDATE_COUNTER = "UpdateCounterDialogFragment"
    const val CORNER = "Corner"
    const val SUPER_STATE = "SuperState"
    const val IS_RETURN = "IsWasReturn"
    const val START_TIME = "StartTime"
}