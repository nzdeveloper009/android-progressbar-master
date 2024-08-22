package com.nokhaiz.androidprogressbar_master

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class PreviewView : View {
    private val paintPaint: Paint
    private var center = Align.CENTER
    private var size = 150f
    private var b = true

    constructor(context: Context) : super(context) {
        paintPaint = Paint()
        paintPaint.color = ContextCompat.getColor(context, R.color.black)
        paintPaint.isAntiAlias = true
        paintPaint.style = Paint.Style.STROKE
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        paintPaint = Paint()
        paintPaint.color = ContextCompat.getColor(context, R.color.black)
        paintPaint.isAntiAlias = true
        paintPaint.style = Paint.Style.STROKE
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paintPaint = Paint()
        paintPaint.color = ContextCompat.getColor(context, R.color.black)
        paintPaint.isAntiAlias = true
        paintPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(0f, canvas.width.toFloat())
        path.lineTo(canvas.height.toFloat(), canvas.width.toFloat())
        path.lineTo(canvas.height.toFloat(), 0f)
        path.lineTo(0f, 0f)
        canvas.drawPath(path, paintPaint)
        paintPaint.textAlign = center
        paintPaint.textSize = size.toString().toFloat()
        canvas.drawText(
            if (b) "32%" else "32", (canvas.width / 2).toFloat(), ((canvas
                .height / 2) - ((paintPaint.descent() + paintPaint
                .ascent()) / 2)).toInt().toFloat(), paintPaint
        )
    }

    fun drawText(size: Int, center: Align, b: Boolean) {
        this.size = size.toFloat()
        this.center = center
        this.b = b
        this.invalidate()
    }
}
