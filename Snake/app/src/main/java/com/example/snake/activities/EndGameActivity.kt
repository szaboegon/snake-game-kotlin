package com.example.snake.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.R
import com.example.snake.adapter.ScoreBoardAdapter
import com.example.snake.data.Player
import com.example.snake.data.SnakeDatabase
import com.example.snake.databinding.ActivityEndGameBinding
import com.example.snake.fragments.NameDialogFragment
import kotlin.concurrent.thread

class EndGameActivity : AppCompatActivity(),NameDialogFragment.NewPlayerDialogListener {
    companion object{
        lateinit var name: String
        var score: Int =0
        var timeElapsed: Int =0
    }
    private lateinit var binding: ActivityEndGameBinding

    private lateinit var database: SnakeDatabase
    private lateinit var adapter: ScoreBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEndGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=SnakeDatabase.getDatabase(applicationContext)
        adapter=ScoreBoardAdapter()

        setListeners()
        score=intent.extras!!.getInt("score")
        timeElapsed=intent.extras!!.getInt("time")
        val scoreString= this.getString(R.string.final_score, score)
        binding.tvFinalScore.text=scoreString
        NameDialogFragment.score= score
        NameDialogFragment.timeElapsed= timeElapsed
        NameDialogFragment().show(supportFragmentManager, "Name")
    }

    override fun onResume() {
        super.onResume()
        binding.btnPlayAgain.alpha=0f
        binding.btnMainMenu.alpha=0f
        playAnimations()
    }

    private fun setListeners(){

        binding.btnPlayAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        binding.btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun onPlayerCreated(newPlayer: Player) {
        thread {
            val insertId = database.playerScoreDao().insert(newPlayer)
            newPlayer.id = insertId
            runOnUiThread {
                adapter.addItem(newPlayer)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun playAnimations(){
        val animBtnNewGame= AnimationUtils.loadAnimation(applicationContext,R.anim.slide_in)
        val animBtnScoreboard= AnimationUtils.loadAnimation(applicationContext,R.anim.slide_in)
        animBtnNewGame.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.btnMainMenu.alpha=1.0f
                binding.btnMainMenu.startAnimation(animBtnScoreboard)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.btnPlayAgain.alpha=1.0f
        binding.btnPlayAgain.startAnimation(animBtnNewGame)
    }


}