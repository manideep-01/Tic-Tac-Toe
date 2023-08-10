package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.tictactoe.databinding.ActivityMainBinding



@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var currentPlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle?.getString("selectedPlayer") != null) {
            // Handle the case when "selectedPlayer" is not null
            val selectedPlayer = bundle.getString("selectedPlayer")
            if (selectedPlayer == "X") {
                currentPlayer = 1
            } else if (selectedPlayer == "O") {
                currentPlayer = 2
            }
        }
    }

    fun clickable(view: View) {
        val buttonSelected = view as ImageButton
        var cellId = 0
        when (buttonSelected.id) {
            R.id.imageButton -> cellId = 1
            R.id.imageButton2 -> cellId = 2
            R.id.imageButton3 -> cellId = 3
            R.id.imageButton4 -> cellId = 4
            R.id.imageButton5 -> cellId = 5
            R.id.imageButton6 -> cellId = 6
            R.id.imageButton7 -> cellId = 7
            R.id.imageButton8 -> cellId = 8
            R.id.imageButton9 -> cellId = 9
        }
        play(cellId, buttonSelected)
        winner()
        resetGame()
    }

    //        Log.d("ImageButton", "clickable: $cellId")

    fun play(cell: Int, imageButton: ImageButton) {
        if (currentPlayer == 1) {
            imageButton.setBackgroundResource(R.drawable.x)
            player1.add(cell)
            currentPlayer = 2
        } else {
            imageButton.setBackgroundResource(R.drawable.o)
            player2.add(cell)
            currentPlayer = 1
        }
        imageButton.isEnabled = false
    }

    fun winner() {
        var winner = 0
        // winning condition for player 1
        // first row
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        // second row
        else if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        // third row
        else if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        // first column
        else if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        // second column
        else if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        // third column
        else if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        // primary diagonal
        else if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        // secondary diagonal
        else if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        // winning condition for player 2
        // first row
        else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        } // second row
        else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        } // third row
        else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        } // first column
        else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        } // second column
        else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        } // third column
        else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        // primary diagonal
        else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        // secondary diagonal
        else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        // Check for draw condition
        val allPositionsFilled = (player1.size + player2.size) == 9

        // Add the sound player for the winning sound
        val mp = MediaPlayer.create(this, R.raw.winning_sound)

        if (winner == 0 && allPositionsFilled) {
            Handler().postDelayed({
                recreate()
            }, 1000)
            Toast.makeText(this@MainActivity, "Game Draw", Toast.LENGTH_SHORT).show()
        } else if (winner == 1) {
            Handler().postDelayed({
                recreate()
            }, 1000)
            Toast.makeText(this@MainActivity, "Player 1 is Winner", Toast.LENGTH_SHORT).show()
            // Play the winning sound
            mp.start()
        } else if (winner == 2) {
            Handler().postDelayed({
                recreate()
            }, 1000)
            Toast.makeText(this@MainActivity, "Player 2 is Winner", Toast.LENGTH_SHORT).show()
            // Play the winning sound
            mp.start()
        }
        // Release the MediaPlayer after the sound is done playing to free up resources
        mp.setOnCompletionListener { mp.release() }
    }

    fun resetGame() {
        binding.resetbutton.setOnClickListener() {
            recreate()
        }
    }
}






