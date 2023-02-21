package com.example.snake.gameLogic
import com.example.snake.views.GameView

class GameController (private val listener: ScoreListener) : GameView.SizeChangeListener{
    var snake: Snake =Snake(Cell(5,7))
    var board: Board = Board(21,15)
    var gameEnded: Boolean = false
    var score: Int=0

    private fun checkCollision(): Boolean {
        for (i in 1 until snake.cells.size){
            if(snake.head.row==snake.cells[i].row && snake.head.col==snake.cells[i].col ){
                return true
            }
        }
        if(snake.head.row<0 || snake.head.row>=board.nRows ||  snake.head.col<0 || snake.head.col>=board.nCols ){
            return true
        }

        return false
    }

    private fun checkFoodCollision(){
        if(snake.head.row==board.food.row && snake.head.col==board.food.col ){
            snake.grow()
            score++
            listener.onScoreChanged(score)
            board.generateFood()
        }
    }

    fun gameLoop(){
        if(snake.moveDirection==Direction.STOPPED)
            return

        snake.move()
        checkFoodCollision()
        if(checkCollision()) {
            gameEnded = true
        }
    }

    interface ScoreListener {
        fun onScoreChanged(score: Int)
    }

    override fun onSizeChanged(nRows: Int) {
        board.nRows=nRows
    }

}