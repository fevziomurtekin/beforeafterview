package com.fevziomurtekin.beforeafter

import android.support.v4.view.ViewCompat.setTranslationY
import android.support.v4.view.ViewCompat.setTranslationX
import android.view.MotionEvent
import android.support.v4.view.ViewCompat.getTranslationY
import android.support.v4.view.ViewCompat.getTranslationX
import android.util.Log
import android.view.GestureDetector
import android.view.View
import android.view.View.OnTouchListener


class MoveViewTouchListener(private val rangeSlider:View,
                            private val mView: View,
                            val onChangedXAxis: OnChangedXAxis) : OnTouchListener {
    private val mGestureDetector: GestureDetector

    private val mGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        private var mMotionDownX: Float = 0.toFloat()

        override fun onDown(e: MotionEvent): Boolean {
            mMotionDownX = e.rawX - mView.translationX
            return true
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if(e2.rawX-mMotionDownX>0 && e2.rawX-mMotionDownX<(rangeSlider.width)-(2.75*mView.width/3)) {
                mView.translationX = e2.rawX - mMotionDownX
                onChangedXAxis.onChangedXAxis(mView.translationX)
            }
            return true
        }
    }

    init {
        mGestureDetector = GestureDetector(mView.context, mGestureListener)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    interface OnChangedXAxis {
        fun onChangedXAxis(xAxis:Float)
    }
}