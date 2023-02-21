package com.example.snake.drawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.example.snake.gameLogic.Snake
import com.example.snake.views.GameView
import kotlin.math.roundToInt

class SnakeDrawer(_snake: Snake) : IDrawable {
    var snake: Snake = _snake
    private var paint: Paint = Paint()
    private var outLinePaint = Paint()

    init {
        paint.color = Color.rgb(21,71,52)
        paint.style = Paint.Style.FILL

        outLinePaint.color=Color.rgb(255,255,255)
        outLinePaint.style = Paint.Style.STROKE
        outLinePaint.strokeWidth=6.0f
    }

    override fun draw(canvas: Canvas){

        for (cell in snake.cells) {
            val rect = Rect(
                (GameView.cellWidth*cell.col).roundToInt(),
                (GameView.cellWidth*cell.row).roundToInt(),
                (GameView.cellWidth*cell.col+GameView.cellWidth).roundToInt(),
                (GameView.cellWidth*cell.row+GameView.cellWidth).roundToInt())

            canvas.drawRect(rect, paint)
            canvas.drawRect(rect,outLinePaint)
        }
    }

}