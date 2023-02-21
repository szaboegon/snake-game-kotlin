package com.example.snake.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.R
import com.example.snake.databinding.ActivityGameBinding
import com.example.snake.drawers.BoardDrawer
import com.example.snake.drawers.SnakeDrawer
import com.example.snake.gameLogic.Direction
import com.example.snake.gameLogic.GameController
import java.util.*

class GameActivity : AppCompatActivity(), GameController.ScoreListener {
    private lateinit var binding: ActivityGameBinding
    var gameController: GameController = GameController(this)
    private var currentScore: Int =0
    var timeElapsed: Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGameBinding.inflate(layoutInflater)
        binding.canvas.calculateDimensions(gameController.board)
        binding.canvas.listener=gameController

        val boardDrawer = BoardDrawer(gameController.board)
        val snakeDrawer = SnakeDrawer(gameController.snake)

        binding.canvas.addDrawable(boardDrawer)
        binding.canvas.addDrawable(snakeDrawer)

        val timeString= this.getString(R.string.current_time, timeElapsed)
        binding.tvTime.text=timeString

        setContentView(binding.root)
        setListeners()
        onScoreChanged(0)
        tick()
    }

    override fun onPause() {
        super.onPause()
        gameController.snake.moveDirection=Direction.STOPPED
    }


    private fun setListeners(){
        val animArrowButton=AnimationUtils.loadAnimation(applicationContext,R.anim.alpha_to1)

        binding.btnUp.setOnClickListener {
            if(gameController.snake.lastStepDirection!=Direction.DOWN) {
                binding.btnUp.startAnimation(animArrowButton)
                gameController.snake.moveDirection = Direction.UP
            }
        }
        binding.btnDown.setOnClickListener {
            if(gameController.snake.lastStepDirection!=Direction.UP) {
                binding.btnDown.startAnimation(animArrowButton)
                gameController.snake.moveDirection = Direction.DOWN
            }
        }
        binding.btnLeft.setOnClickListener {
            if(gameController.snake.lastStepDirection!=Direction.RIGHT) {
                binding.btnLeft.startAnimation(animArrowButton)
                gameController.snake.moveDirection = Direction.LEFT
            }
        }
        binding.btnRight.setOnClickListener {
            if(gameController.snake.lastStepDirection!=Direction.LEFT) {
                binding.btnRight.startAnimation(animArrowButton)
                gameController.snake.moveDirection = Direction.RIGHT
            }
        }
    }

    private fun tick(){
        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run() {
                gameController.gameLoop()
                binding.canvas.invalidate()
                if(gameController.gameEnded){
                    endGame()
                    cancel()
                }
            }
        }, 0, 150)

        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run() {
                if(gameController.snake.moveDirection==Direction.STOPPED)
                    return

                timeElapsed++
                println(timeElapsed)
                val timeString= applicationContext.getString(R.string.current_time, timeElapsed)
                binding.tvTime.text=timeString
            }
        }, 0, 1000)
    }

    fun endGame(){
        val intent = Intent(this, EndGameActivity::class.java)
        intent.putExtra("score",currentScore)
        intent.putExtra("time",timeElapsed)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onScoreChanged(score: Int) {
        currentScore=score
        val scoreString= this.getString(R.string.current_score, currentScore)
        runOnUiThread {
            binding.tvScore.text=scoreString
        }
    }


}