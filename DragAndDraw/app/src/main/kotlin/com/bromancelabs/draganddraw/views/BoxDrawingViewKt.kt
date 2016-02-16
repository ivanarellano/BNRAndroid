package com.bromancelabs.draganddraw.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.bromancelabs.draganddraw.models.Box
import java.util.*

class BoxDrawingViewKt : View {
    companion object {
        val TAG = BoxDrawingViewKt::class.java.simpleName
    }

    var currentBox: Box? = null
    var boxList = ArrayList<Box>()
    val boxPaint = Paint()
    val backgroundPaint = Paint()

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        boxPaint.color = 0x22ff0000
        backgroundPaint.color = 0xfff8efe0.toInt()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentPoint = PointF(event.x, event.y)
        var action = ""

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"
                currentBox = Box(currentPoint)
                boxList.add(currentBox as Box)
            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"
                currentBox?.current = currentPoint
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"
                currentBox = null
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
                currentBox = null
            }
        }

        Log.d(TAG, "$action at x=${currentPoint.x}, y=${currentPoint.y}")
        return true
    }

    override fun onDraw(canvas: Canvas) {
        // Fill the background
        canvas.drawPaint(backgroundPaint)

        fun drawRect(left: Float, right: Float, top: Float, bottom: Float) {
            canvas.drawRect(left, top, right, bottom, boxPaint)
        }

        for (box in boxList) {
            drawRect(left = Math.min(box.origin.x, box.current.x),
                     right = Math.max(box.origin.x, box.current.x),
                     top = Math.min(box.origin.y, box.current.y),
                     bottom = Math.max(box.origin.y, box.current.y))
        }
    }
}