package com.example.multithreadcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BarsView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        val MAX_VALUE = 100
    }

    val linePaint = Paint()
    val linePaint2 = Paint()
    val linePaint3 = Paint()

    var values: Array<Int>? = null
    var values2: Array<Int>? = null
    var values3: Array<Int>? = null

    init {
        linePaint.style = Paint.Style.STROKE
        linePaint.color = Color.BLUE

        linePaint2.style = Paint.Style.STROKE
        linePaint2.color = Color.RED

        linePaint3.style = Paint.Style.STROKE
        linePaint3.color = Color.YELLOW
    }

    fun setData(idx: Int, arr: Array<Int>) {
        when (idx) {
            0 -> values = arr
            1 -> values2 = arr
            2 -> values3 = arr
        }
        invalidate()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if (canvas != null && (values != null || values2 != null || values3 != null)) {
            for (idx in 0 until height / 2) {
                /*if (values != null && idx < values!!.size) {
                    val yy = idx * 2
                    val w = width * (values!![idx].toFloat() / MAX_VALUE)
                    canvas.drawLine(0f, yy.toFloat(), w, yy.toFloat(), linePaint)
                }

                if (values2 != null && idx < values2!!.size) {
                    val yy = idx * 2 + 1
                    val w = width * (values2!![idx].toFloat() / MAX_VALUE)
                    canvas.drawLine(0f, yy.toFloat(), w, yy.toFloat(), linePaint2)
                }*/
                if (values3 != null && idx < values3!!.size) {
                    val yy = idx * 2
                    val w = width * (values3!![idx].toFloat() / MAX_VALUE)
                    canvas.drawLine(0f, yy.toFloat(), w, yy.toFloat(), linePaint3)
                }
            }
        }
    }

}