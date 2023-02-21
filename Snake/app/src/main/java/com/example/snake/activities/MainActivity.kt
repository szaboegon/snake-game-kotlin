package com.example.snake.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.R
import com.example.snake.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNewGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        binding.btnScoreBoard.setOnClickListener {
            val intent = Intent(this, ScoreBoardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnNewGame.alpha=0f
        binding.btnScoreBoard.alpha=0f
        playAnimations()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", { _,_ -> this.finish() })
            .setNegativeButton("No", null)
            .show()
    }

    private fun playAnimations(){
        val animBtnNewGame=AnimationUtils.loadAnimation(applicationContext,R.anim.slide_in)
        val animBtnScoreboard=AnimationUtils.loadAnimation(applicationContext,R.anim.slide_in)
        animBtnNewGame.setAnimationListener(object: AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.btnScoreBoard.alpha=1.0f
                binding.btnScoreBoard.startAnimation(animBtnScoreboard)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.btnNewGame.alpha=1.0f
        binding.btnNewGame.startAnimation(animBtnNewGame)
    }
}

