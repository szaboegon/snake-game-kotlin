package com.example.snake.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.snake.data.Player
import com.example.snake.databinding.ScoreboardItemBinding

class ScoreBoardAdapter :
    RecyclerView.Adapter<ScoreBoardAdapter.ScoreBoardViewHolder>(){

    private val players = mutableListOf<Player>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScoreBoardViewHolder(
        ScoreboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ScoreBoardViewHolder, position: Int) {
        val player = players[position]
        holder.binding.tvRank.text=(players.indexOf(player)+1).toString()
        holder.binding.tvName.text=player.name
        holder.binding.tvScore.text=("${player.score} pts").toString()
        holder.binding.tvTime.text=("${player.time}s")
    }

    override fun getItemCount(): Int = players.size

    fun addItem(item: Player) {
        players.add(item)
        players.sortByDescending { it.time  }
        players.sortByDescending { it.score  }
        notifyDataSetChanged()
    }


    fun update(p: List<Player>) {
        players.clear()
        players.addAll(p)
        players.sortByDescending { it.time  }
        players.sortByDescending { it.score  }
        notifyDataSetChanged()
    }

    inner class ScoreBoardViewHolder(val binding: ScoreboardItemBinding) : RecyclerView.ViewHolder(binding.root)
    }

