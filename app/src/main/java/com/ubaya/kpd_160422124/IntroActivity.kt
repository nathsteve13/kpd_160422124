package com.ubaya.kpd_160422124

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ubaya.kpd_160422124.databinding.ActivityIntroBinding
import com.ubaya.kpd_160422124.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    companion object {
        val PLAYER_NAME = "player_name"
    }

    private fun showDatePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->

                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val displayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val displayDate = displayFormat.format(selectedDate.time)
                binding.txtBod.setText(displayDate)

                val storageFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val storedDate = storageFormat.format(selectedDate.time)
                binding.txtBod.tag = storedDate

            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadMovie() {
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressWebView.visibility = View.INVISIBLE
            }
        }

        val videoId = "es7W-hGH99M?si=1Pc16JeuBnHMkr8k"
        val youtubeUrl = "https://www.youtube.com/embed/$videoId"

        binding.webView.loadUrl(youtubeUrl)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            val playerName = binding.txtPlayerName.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(PLAYER_NAME, playerName)
            startActivity(intent)
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, QuestionListActivity::class.java)
            startActivity(intent)
        }

        binding.txtBod.setOnClickListener { showDatePickerDialog() }

        loadMovie()
//        setContentView(R.layout.activity_intro)
    }
}