package com.ubaya.kpd_160422124

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ubaya.kpd_160422124.databinding.ActivityNewQuestionBinding

class NewQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = arrayOf("karen", "mermaid", "mrkrab", "sponge", "squid")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerImage.adapter = adapter


        binding.labelImageURL.visibility = View.GONE
        binding.txtURL.visibility = View.GONE

        binding.radioTemplate.setOnClickListener {
            with(binding) {
                labelChooseImage.visibility = View.VISIBLE
                spinnerImage.visibility = View.VISIBLE
                labelImageURL.visibility = View.GONE
                txtURL.visibility = View.GONE
            }
        }

        binding.radioExternal.setOnClickListener {
            with(binding) {
                labelChooseImage.visibility = View.GONE
                spinnerImage.visibility = View.GONE
                labelImageURL.visibility = View.VISIBLE
                txtURL.visibility = View.VISIBLE
            }
        }

        val imgid = this.resources.getIdentifier("karen", "drawable", this.packageName)
        binding.imgPreview.setImageResource(imgid)

        binding.spinnerImage.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                val imgid = applicationContext.resources.getIdentifier(items[position],
                    "drawable", applicationContext.packageName)
                binding.imgPreview.setImageResource(imgid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        binding.btnSave.setOnClickListener {
            val radioAnswer = findViewById<RadioButton>(binding.radioAnswer.
            checkedRadioButtonId)
            val selectedImageName = items[binding.spinnerImage.selectedItemPosition]
            val imgid = this.resources.getIdentifier(selectedImageName, "drawable",
                this.packageName)
            val newQuestion = QuestionBank.QuestionBank(
                binding.txtNewQuestion.text.toString(),
                radioAnswer.text.toString().lowercase().toBoolean(),
                imgid
            )

            val questionsList = QuestionData.question.toMutableList()
            questionsList.add(newQuestion)
            QuestionData.question = questionsList.toTypedArray()

            Toast.makeText(this, "Question Added", Toast.LENGTH_SHORT).show()

            finish()


        }

    }
}