package com.example.snake.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.snake.data.Player
import com.example.snake.databinding.DialogfragmentNameBinding


class NameDialogFragment: DialogFragment(){
    interface NewPlayerDialogListener {
        fun onPlayerCreated(newPlayer: Player)
    }

    companion object {
        var score: Int=0
        var timeElapsed: Int=0
    }

    private lateinit var listener: NewPlayerDialogListener

    private lateinit var binding: DialogfragmentNameBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewPlayerDialogListener
            ?: throw RuntimeException("Activity does not implement required interface")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogfragmentNameBinding.inflate(LayoutInflater.from(context))

        return androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Please enter your name")
            .setView(binding.root)
            .setPositiveButton("Ok") { _,_ ->
                if (isValid()) {
                    listener.onPlayerCreated(getPlayer())
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun isValid() = binding.etEnterName.text.isNotEmpty()

    private fun getPlayer() = Player(
        name=binding.etEnterName.text.toString(),
        score = score,
        time = timeElapsed
    )

}