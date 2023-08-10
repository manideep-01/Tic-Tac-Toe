package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityChoosePlayerBinding

class ChoosePlayerActivity : AppCompatActivity() {
   private lateinit var binding : ActivityChoosePlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonX.setOnClickListener {
            choosePlayer("X")
        }

        binding.imageButtonO.setOnClickListener {
            choosePlayer("O")
        }

    }

    private fun choosePlayer(selectedPlayer: String? = null) {
        val resultIntent = Intent(this@ChoosePlayerActivity,MainActivity::class.java)
        resultIntent.putExtra("selectedPlayer", selectedPlayer)
        startActivity(resultIntent)
    }
}