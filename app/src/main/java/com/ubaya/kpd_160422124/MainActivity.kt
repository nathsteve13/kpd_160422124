package com.ubaya.kpd_160422124

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ubaya.kpd_160422124.IntroActivity.Companion.PLAYER_NAME
import com.ubaya.kpd_160422124.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var curScore  = 0
    }

    var currentQuestion = 0
    var CUR_SCORE = ""
    var ansWrong = 0


    fun displayQuestions() {
        binding.txtQuestionTitle.text = QuestionData.question[currentQuestion].question
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra(IntroActivity.PLAYER_NAME)

        displayQuestions()
        binding.txtName.text = playerName
        binding.txtScoreNow.text = "Score : " + curScore

        val intent = Intent(this, ResultActivity::class.java)

        binding.btnTrue.setOnClickListener {
            if(QuestionData.question[currentQuestion].answer == true) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                curScore++
                currentQuestion++
                if (currentQuestion == 5) {
                    intent.putExtra(CUR_SCORE, curScore)
                    startActivity(intent)
                } else {
                    displayQuestions()
                    binding.txtScoreNow.text = "Score : " + curScore
                }
            } else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                ansWrong++

                intent.putExtra(CUR_SCORE, curScore)
                startActivity(intent)
            }

        }

        binding.btnFalse.setOnClickListener {
            if(QuestionData.question[currentQuestion].answer == false) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                curScore++
                currentQuestion++
                if (currentQuestion == 5) {
                    intent.putExtra(CUR_SCORE, curScore)
                    startActivity(intent)
                } else {
                    displayQuestions()
                    binding.txtScoreNow.text = "Score : " + curScore
                }
            } else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                ansWrong++

                intent.putExtra(CUR_SCORE, curScore)
                startActivity(intent)
            }

            currentQuestion++
            displayQuestions()
        }
//        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)
//        val btnTrue = findViewById<Button>(R.id.btnTrue)
//        val btnFalse = findViewById<Button>(R.id.btnFalse)
//
//        btnTrue.setOnClickListener {
//            txtQuestion.text = "Spongebob Quiz"
//        }
    }
}