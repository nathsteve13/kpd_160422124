package com.ubaya.kpd_160422124

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ubaya.kpd_160422124.databinding.ActivityMainBinding
import com.ubaya.kpd_160422124.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayListScore = arrayListOf<Int>()
        val endScore = intent.getIntExtra("", MainActivity.curScore)

        arrayListScore.add(endScore)
        val highScore = arrayListScore.maxOrNull()

        binding.txtScore.text = endScore.toString()
        binding.txtHighScore.text = highScore.toString()

        binding.btnReplay.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}