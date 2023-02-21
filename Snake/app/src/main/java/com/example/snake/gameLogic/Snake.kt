package com.example.snake.gameLogic
import java.util.*

class Snake (_head: Cell) {
    var cells: ArrayList<Cell> = ArrayList<Cell>()
    var head: Cell = _head
    private var nStartingCells: Int = 5
    var moveDirection: Direction = Direction.STOPPED
    var lastStepDirection = Direction.UP

    init {
        cells.add(head)
        var r=head.row
        val c=head.col
        r++
        for(i in 0 until nStartingCells-1){
            cells.add(Cell(r++,c))
        }
    }

    fun move(){
        if(moveDirection==Direction.STOPPED) {
            return
        }

        for (i in cells.lastIndex downTo 1) {
            cells[i].row = cells[i - 1].row
            cells[i].col = cells[i - 1].col
        }

        when (moveDirection) {
            Direction.UP -> {
                head.row = head.row - 1
                lastStepDirection=Direction.UP
            }

            Direction.DOWN -> {
                head.row = head.row + 1
                lastStepDirection=Direction.DOWN
            }

            Direction.LEFT -> {
                head.col = head.col - 1
                lastStepDirection=Direction.LEFT
            }

            Direction.RIGHT -> {
                head.col = head.col + 1
                lastStepDirection=Direction.RIGHT
            }

            Direction.STOPPED->{}

        }
    }

    fun grow(){
        cells.add(Cell(cells.last().row, cells.last().col))
    }

}