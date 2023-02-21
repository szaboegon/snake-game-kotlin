package com.example.snake.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.view.doOnLayout
import com.example.snake.drawers.IDrawable
import com.example.snake.gameLogic.Board
import com.example.snake.gameLogic.GameController
import kotlin.math.roundToInt


class GameView (context: Context?, attrs: AttributeSet? ) : View(context, attrs) {

    companion object{
        var cellWidth: Float=0.0f
    }
    private var drawables: ArrayList<IDrawable> = ArrayList()
    lateinit var listener: GameController


    fun addDrawable(d: IDrawable){
        drawables.add(d)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for(drawable in drawables){
            drawable.draw(canvas)
        }
    }

     fun calculateDimensions(board:Board) {
         this.doOnLayout {
             cellWidth = (width * 1.0f) / (board.nCols * 1.0f)
             val nRows: Int= (height / cellWidth).roundToInt()
             listener.onSizeChanged(nRows)
         }
    }

    interface SizeChangeListener{
        fun onSizeChanged(nRows: Int)
    }


}

