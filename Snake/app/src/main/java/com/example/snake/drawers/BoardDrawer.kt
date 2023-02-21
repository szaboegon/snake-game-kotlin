package com.example.snake.drawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.snake.gameLogic.Board
import com.example.snake.views.GameView

class BoardDrawer(_board: Board) : IDrawable {
    private var board: Board= _board
    private var paint: Paint = Paint()
    private var outLinePaint = Paint()

    init {
        paint.color = Color.rgb(248,105,105)
        paint.style = Paint.Style.FILL

        outLinePaint.color=Color.rgb(255,255,255)
        outLinePaint.style = Paint.Style.STROKE
        outLinePaint.strokeWidth=6.0f
    }

    override fun draw(canvas: Canvas){
        canvas.drawCircle(GameView.cellWidth*board.food.col+GameView.cellWidth/2,
            GameView.cellWidth*board.food.row+GameView.cellWidth/2,GameView.cellWidth/2,paint)

        canvas.drawCircle(GameView.cellWidth*board.food.col+GameView.cellWidth/2,
            GameView.cellWidth*board.food.row+GameView.cellWidth/2,GameView.cellWidth/2,outLinePaint)
    }
}