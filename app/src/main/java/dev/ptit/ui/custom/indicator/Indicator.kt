package dev.ptit.ui.custom.indicator

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import dev.ptit.R

class Indicator : View {

    private var activeIndicator = 0
    private val indicatorPaintList = mutableListOf<Paint>()
    private var activeColor = context.getColor(android.R.color.black)
    private var inactiveColor = context.getColor(android.R.color.white)
    private var indicatorNumber = 3

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs, defStyleAttr)
        initView()
    }

    private fun initView() {
        indicatorPaintList.clear()
        repeat(indicatorNumber) {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.FILL
            }
            indicatorPaintList.add(paint)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        indicatorPaintList.forEachIndexed { index, paint ->
            paint.color = if (index == activeIndicator) activeColor else inactiveColor
            val left = index * getIndicatorSize() * 2f
            val top = 0f
            val right = left + getIndicatorSize()
            val bottom = getIndicatorSize().toFloat()
            canvas.drawOval(left, top, right, bottom, paint)
        }
    }

    fun setActiveIndicator(activeIndicator: Int) {
        this.activeIndicator = activeIndicator
        invalidate()
    }

    fun setIndicatorNumber(indicatorNumber: Int) {
        this.indicatorNumber = indicatorNumber
        Log.d("Indicator", "indicatorNumber: $indicatorNumber")
        initView()
        invalidate()
    }


    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicator)
        updateView(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.Indicator, defStyleAttr, 0)
        updateView(typedArray)
    }

    private fun updateView(typedArray: TypedArray) {
        activeColor = typedArray.getColor(R.styleable.Indicator_activeColor, activeColor)
        inactiveColor =
            typedArray.getColor(R.styleable.Indicator_inactiveColor, inactiveColor)
        indicatorNumber =
            typedArray.getInt(R.styleable.Indicator_indicatorNumber, indicatorNumber)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                widthSize
            }

            else -> {
                calculateDesiredWidthSize(heightMode, heightSize)
            }
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                heightSize
            }

            else -> {
                calculateDesiredHeightSize(widthMode, widthSize)

            }
        }
        setMeasuredDimension(width, height)
    }

    private fun calculateDesiredWidthSize(heightMode: Int, heightSize: Int): Int {
        return if (heightMode == MeasureSpec.EXACTLY) {
            heightSize * (2 * indicatorNumber - 1)
        } else {
            DESIRED_INDICATOR_SIZE * (2 * indicatorNumber - 1)
        }
    }

    private fun calculateDesiredHeightSize(widthMode: Int, widthSize: Int): Int {
        return if (widthMode == MeasureSpec.EXACTLY) {
            widthSize / (2 * indicatorNumber - 1)
        } else {
            DESIRED_INDICATOR_SIZE
        }
    }

    private fun getIndicatorSize(): Int {
        return if (height <= 0) DESIRED_INDICATOR_SIZE else height
    }

    companion object {
        private const val DESIRED_INDICATOR_SIZE = 20
    }
}