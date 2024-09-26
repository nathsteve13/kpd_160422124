package com.ubaya.kpd_160422124

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kpd_160422124.databinding.ActivityQuestionListBinding

class QuestionListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recQuestion.layoutManager = LinearLayoutManager(this)
        binding.recQuestion.setHasFixedSize(true)
        binding.recQuestion.adapter = QuestionAdapter()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, NewQuestionActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        binding.recQuestion.layoutManager = LinearLayoutManager(this)
        binding.recQuestion.setHasFixedSize(true)
        binding.recQuestion.adapter = QuestionAdapter()
    }


}