package com.ubaya.kpd_160422124

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ubaya.kpd_160422124.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var curScore = 0
    }

    private var currentQuestion = 0
    private val CUR_SCORE = "curScore"
    private var ansWrong = 0

    private fun displayQuestions() {
        binding.txtQuestionTitle.text = QuestionData.question[currentQuestion].question
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Reset score and questions for a new game
        curScore = 0
        currentQuestion = 0
        ansWrong = 0

        val playerName = intent.getStringExtra(IntroActivity.PLAYER_NAME)

        displayQuestions()
        binding.txtName.text = playerName
        binding.txtScoreNow.text = "Score : $curScore"

        binding.btnTrue.setOnClickListener {
            handleAnswer(true)
        }

        binding.btnFalse.setOnClickListener {
            handleAnswer(false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleAnswer(userAnswer: Boolean) {
        val correctAnswer = QuestionData.question[currentQuestion].answer
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            curScore++
        } else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
            ansWrong++
        }

        currentQuestion++

        if (currentQuestion >= QuestionData.question.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(CUR_SCORE, curScore)
            startActivity(intent)
            finish()
        } else {
            displayQuestions()
            binding.txtScoreNow.text = "Score : $curScore"
        }
    }
}
