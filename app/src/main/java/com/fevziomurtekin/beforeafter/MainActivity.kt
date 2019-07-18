package com.fevziomurtekin.beforeafter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), MoveViewTouchListener.OnChangedXAxis {

    private var rangeWidth=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mark.setOnTouchListener(MoveViewTouchListener(mark,iv_slider,this))
        iv_after.imageAlpha = 0
        iv_before.imageAlpha = 255

    }

    override fun onChangedXAxis(xAxis: Float) {
        Log.e("X axis : ", "xAxis :$xAxis  rangeWidth: $rangeWidth")
        if(xAxis>0 && xAxis<rangeWidth) {
            val alphaToView = ((xAxis * 255) / rangeWidth).roundToInt()
            iv_before.imageAlpha = 255 - alphaToView
            iv_after.imageAlpha = alphaToView
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        rangeWidth = mark.width
    }

}
