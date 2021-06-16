package com.thiraithal.utils


import android.R.attr.path
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.thiraithal.R


class RoundedRelativeLayout(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private var rect: RectF? = null
    private var mCornerRadius = 10
    private var height : Float = 0.0f
    private  var width: Float = 0.0f

/*
    fun RoundedRelativeLayout(
        context: Context?,
        attrs: AttributeSet?
    ) {
        onInit()
    }*/

    init {
        onInit()
    }

    protected fun onInit() {
        mCornerRadius = resources.getDimensionPixelOffset(R.dimen.rounded_corner_radius_test)
        setWillNotDraw(false)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w !== oldw && h !== oldh) {
            rect = RectF(0f, 0f, w.toFloat(), h.toFloat())
            height = h.toFloat()
            width = w.toFloat()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun dispatchDraw(canvas: Canvas?) {
        canvas!!.drawFilter = PaintFlagsDrawFilter(1, Paint.ANTI_ALIAS_FLAG)
        val radiusArr = floatArrayOf(
            25f, 25f,
            25f, 25f,
            50f, 50f,
            50f, 50f
        )
        val clipPath = Path()

       // clipPath.addRoundRect(rect, mCornerRadius.toFloat(), mCornerRadius.toFloat(), Path.Direction.CW)

        clipPath.addRoundRect(
            RectF(0f, 0f, getWidth().toFloat(), getHeight().toFloat()),
            radiusArr, Path.Direction.CW
        )


        // clipPath.addRoundRect(rect, radiusArr, Path.Direction.CW)
        // clipPath.addRoundRect(rect, mCornerRadius.toFloat(), mCornerRadius.toFloat(), Path.Direction.CW)
      // clipPath.addRoundRect(rect, height, width,Path.Direction.CW )
        //  clipPath.addRoundRect(rect, radiusArr, Path.Direction.CW)

        canvas!!.clipPath(clipPath)
        super.dispatchDraw(canvas)
    }
}