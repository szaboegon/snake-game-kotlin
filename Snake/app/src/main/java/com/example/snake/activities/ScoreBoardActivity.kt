package com.example.snake.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snake.R
import com.example.snake.adapter.ScoreBoardAdapter
import com.example.snake.data.Player
import com.example.snake.data.SnakeDatabase
import com.example.snake.databinding.ActivityScoreBoardBinding
import kotlin.concurrent.thread

class ScoreBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBoardBinding

    private lateinit var database: SnakeDatabase
    private lateinit var adapter: ScoreBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = SnakeDatabase.getDatabase(applicationContext)

        initRecyclerView()


        binding.btnBackToMenu.setOnClickListener {
            onBackPressed()
        }
        binding.btnResetScores.setOnClickListener {
            thread {
                database.clearAllTables()
                runOnUiThread{
                    val p = mutableListOf<Player>()
                    adapter.update(p)
                }
            }
        }

    }

    private fun initRecyclerView() {
        adapter = ScoreBoardAdapter()
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val players = database.playerScoreDao().getAll()
            runOnUiThread {
                adapter.update(players)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}