package com.ubaya.kpd_160422124

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ubaya.kpd_160422124.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("game_prefs", MODE_PRIVATE)

        val endScore = intent.getIntExtra("curScore", 0)
        binding.txtScore.text = endScore.toString()

        val highScore = sharedPreferences.getInt("high_score", 0)

        if (endScore > highScore) {
            sharedPreferences.edit().putInt("high_score", endScore).apply()
        }

        val updatedHighScore = sharedPreferences.getInt("high_score", 0)
        binding.txtHighScore.text = updatedHighScore.toString()

        binding.btnReplay.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
