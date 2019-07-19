package com.fevziomurtekin.beforeafterview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import kotlin.math.roundToInt

class BeforeAfterView :ConstraintLayout,GestureListener.OnChangedXAxis{

    /** Views on layout.*/
    private lateinit var cl_root: ConstraintLayout
    private lateinit var iv_after: ImageView
    private lateinit var iv_before: ImageView
    private lateinit var iv_slider: ImageView
    private lateinit var slider_view: View
    private lateinit var mark: ConstraintLayout

    /** attributes of layout */
    private var bgColor = resources.getColor(android.R.color.black)
    private var beforeSrc = R.drawable.before
    private var afterSrc = R.drawable.after
    private var sliderTintColor = resources.getColor(android.R.color.white)
    private var sliderIconTint = resources.getColor(android.R.color.white)

    private var rangeWidth=0

    constructor(context: Context) : super(context) { init(context,null,0,0) }

    constructor(context: Context,attrs: AttributeSet?) : super(context,attrs){ init(context,attrs,0,0)}

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int) :super(context,attrs,defStyleAttr){ init(context,attrs,defStyleAttr,0)}


    private fun init(context: Context,attrs: AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.lib_layout,null)

        this@BeforeAfterView.addView(layout.findViewById(R.id.root))

        context.theme?.obtainStyledAttributes(attrs, R.styleable.BeforeAfterView, defStyleAttr, defStyleRes).let {
            if(it!=null){
                bgColor=it.getInt(R.styleable.BeforeAfterView_bgColor, resources.getColor(android.R.color.black))
                beforeSrc = it.getInt(R.styleable.BeforeAfterView_beforeSrc,R.drawable.before)
                afterSrc = it.getInt(R.styleable.BeforeAfterView_afterSrc,R.drawable.after)
                sliderTintColor=it.getInt(R.styleable.BeforeAfterView_sliderTintColor,resources.getColor(android.R.color.white))
                sliderIconTint=it.getInt(R.styleable.BeforeAfterView_sliderIconTint,resources.getColor(android.R.color.white))
                initViews()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews(){
        cl_root = this@BeforeAfterView.findViewById(R.id.root)
        iv_after = this@BeforeAfterView.findViewById(R.id.iv_after)
        iv_before = this@BeforeAfterView.findViewById(R.id.iv_before)
        iv_slider = this@BeforeAfterView.findViewById(R.id.iv_slider)
        slider_view = this@BeforeAfterView.findViewById(R.id.slider_view)
        mark = this@BeforeAfterView.findViewById(R.id.mark)

        cl_root.setBackgroundColor(bgColor)
        iv_after.setImageResource(afterSrc)
        iv_before.setImageResource(beforeSrc)

        iv_after.scaleType = ImageView.ScaleType.FIT_CENTER
        iv_before.scaleType = ImageView.ScaleType.FIT_CENTER

        slider_view.backgroundTintList = ColorStateList.valueOf(sliderTintColor)
        iv_slider.backgroundTintList = ColorStateList.valueOf(sliderIconTint)

        iv_after.imageAlpha = 0
        iv_before.imageAlpha = 255

        mark.setOnTouchListener(GestureListener(mark, iv_slider, this))

    }


    override fun onChangedXAxis(xAxis: Float) {
        Log.e("X axis : ", "xAxis :$xAxis  rangeWidth: $rangeWidth")
        if(xAxis>0 && xAxis<rangeWidth) {
            val alphaToView = ((xAxis * 255) / rangeWidth).roundToInt()
            iv_before.imageAlpha = 255 - alphaToView
            iv_after.imageAlpha = alphaToView
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        rangeWidth = mark.width
    }


}