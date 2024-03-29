package com.patrick.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val cyanPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        // 하늘색 페인트
        cyanPaint.color = Color.CYAN
        // 검은색 테두리 페인트
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 바깥 원
        canvas?.drawCircle(cX, cY, 100f, blackPaint)
        // 하늘색 원
        canvas?.drawCircle(cX + xCoord, cY + yCoord, 100f, cyanPaint)
        //가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)
    }

    fun onSensorEvent(event: SensorEvent) {
        // 화면을 가로로 고정했으므로 x축과 y축을 서로 바꿈
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        // 뷰를 다시 그림
        invalidate()
    }
}