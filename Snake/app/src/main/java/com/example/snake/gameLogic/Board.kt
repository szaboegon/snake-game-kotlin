package com.example.snake.gameLogic

import kotlin.random.Random

class Board (_nRows: Int, _nCols: Int){
    var nRows: Int = _nRows
    var nCols: Int = _nCols
    var food: Cell

    init {
        food=Cell(3,3)
    }

    fun generateFood(){
        val row= Random.nextInt(0, nRows)
        val col= Random.nextInt(0, nCols)

        food=Cell(row,col)
    }

}