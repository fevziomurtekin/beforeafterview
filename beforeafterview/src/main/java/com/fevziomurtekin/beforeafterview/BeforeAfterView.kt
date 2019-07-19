package com.fevziomurtekin.beforeafterview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Point
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.Guideline
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.lib_layout.view.*
import kotlin.math.roundToInt

class BeforeAfterView :ConstraintLayout,GestureListener.OnChangedXAxis{

    /** Views on layout.*/
    private lateinit var cl_root: ConstraintLayout
    private lateinit var iv_after: ImageView
    private lateinit var iv_before: ImageView
    private lateinit var iv_slider: ImageView
    private lateinit var slider_view: View
    private lateinit var mark: ConstraintLayout
    private lateinit var rv_images:RelativeLayout

    /** attributes of layout */
    private var bgColor = resources.getColor(android.R.color.black)
    private var beforeSrc = R.drawable.before
    private var afterSrc = R.drawable.after
    private var sliderTintColor = resources.getColor(android.R.color.white)
    private var sliderIconTint = resources.getColor(android.R.color.white)
    private var imageHeightPercent:Float = 0.5f
    private var sliderWidthPercent:Float = 0.75f

    private var rangeWidth=0
    private var screenHeight =0

    constructor(context: Context) : super(context) { init(context,null,0,0) }

    constructor(context: Context,attrs: AttributeSet?) : super(context,attrs){ init(context,attrs,0,0)}

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int) :super(context,attrs,defStyleAttr){ init(context,attrs,defStyleAttr,0)}


    private fun init(context: Context,attrs: AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
        context.theme?.obtainStyledAttributes(attrs, R.styleable.BeforeAfterView, defStyleAttr, defStyleRes).let {
            if(it!=null){
                bgColor=it.getInt(R.styleable.BeforeAfterView_bgColor, resources.getColor(android.R.color.black))
                beforeSrc = it.getInt(R.styleable.BeforeAfterView_beforeSrc,R.drawable.before)
                afterSrc = it.getInt(R.styleable.BeforeAfterView_afterSrc,R.drawable.after)
                sliderTintColor=it.getInt(R.styleable.BeforeAfterView_sliderTintColor,resources.getColor(android.R.color.white))
                sliderIconTint=it.getInt(R.styleable.BeforeAfterView_sliderIconTint,resources.getColor(android.R.color.white))
                imageHeightPercent=it.getFloat(R.styleable.BeforeAfterView_imageHeightPercent,imageHeightPercent)
                sliderWidthPercent=it.getFloat(R.styleable.BeforeAfterView_sliderWidthPercent,sliderWidthPercent)
                initViews()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews(){
        this@BeforeAfterView.removeAllViews()
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.lib_layout,null)
        this@BeforeAfterView.addView(layout.findViewById(R.id.root))
        this@BeforeAfterView.findViewById<ConstraintLayout>(R.id.root).apply {
            (layoutParams as LayoutParams).height = screenHeight

        }

        cl_root = this@BeforeAfterView.findViewById(R.id.root)
        iv_after = this@BeforeAfterView.findViewById(R.id.iv_after)
        iv_before = this@BeforeAfterView.findViewById(R.id.iv_before)
        iv_slider = this@BeforeAfterView.findViewById(R.id.iv_slider)
        slider_view = this@BeforeAfterView.findViewById(R.id.slider_view)
        mark = this@BeforeAfterView.findViewById(R.id.mark)
        rv_images = this@BeforeAfterView.findViewById(R.id.rv_images)

        cl_root.setBackgroundColor(bgColor)
        iv_after.setImageResource(afterSrc)
        iv_before.setImageResource(beforeSrc)

        imageHeightPercent = if(imageHeightPercent>1f) 0.55f else imageHeightPercent
        sliderWidthPercent = if(sliderWidthPercent>1f) 0.65f else sliderWidthPercent

        (rv_images.layoutParams as LayoutParams).matchConstraintPercentHeight = imageHeightPercent
        (mark.layoutParams as LayoutParams).matchConstraintPercentWidth = sliderWidthPercent

        slider_view.backgroundTintList = ColorStateList.valueOf(sliderTintColor)
        iv_slider.imageTintList = ColorStateList.valueOf(sliderIconTint)

        iv_after.imageAlpha = 0
        iv_before.imageAlpha = 255

        mark.setOnTouchListener(GestureListener(mark, iv_slider, this))

    }


    override fun onChangedXAxis(xAxis: Float) {
        if(xAxis>0 && xAxis<rangeWidth) {
            val alphaToView = ((xAxis * 255) / rangeWidth).roundToInt()
            iv_before.imageAlpha = 255 - alphaToView
            iv_after.imageAlpha = alphaToView
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        rangeWidth = mark.width
        screenHeight =(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height
        initViews()

    }


}