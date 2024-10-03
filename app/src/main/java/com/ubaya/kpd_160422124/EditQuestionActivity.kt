package com.ubaya.kpd_160422124

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.ubaya.kpd_160422124.databinding.ActivityNewQuestionBinding

class EditQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityNewQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = arrayOf("karen", "mermaid", "mrkrab", "sponge", "squid")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerImage.adapter = adapter

        binding.spinnerImage.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                val imgid = applicationContext.resources.getIdentifier(items[position],
                    "drawable", applicationContext.packageName)
                binding.imgPreview.setImageResource(imgid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        binding.txtURL.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER) {
                val url = binding.txtURL.text.toString()
                val builder = Picasso.Builder(this)
                builder.listener { picasso, uri, exception ->
                    exception.printStackTrace()
                }
                builder.build().load(url).into(binding.imgPreview)
            }
            true
        }

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

        val index = intent.getIntExtra("question_index", 0)
        binding.txtNewQuestion.setText(QuestionData.question[index].question)
        binding.txtURL.setText(QuestionData.question[index].url)
        binding.checkAvailable.isChecked = QuestionData.question[index].isAvailable

        if(QuestionData.question[index].answer) {
            binding.radioTrue.isChecked = true
        } else {
            binding.radioFalse.isChecked = true
        }

        val imageName = resources.getResourceEntryName(QuestionData.question[index].imageId)
        val spinnerIndex = items.indexOf(imageName)
        binding.spinnerImage.setSelection(spinnerIndex)

        if(QuestionData.question[index].url!= "") {
            binding.radioExternal.isChecked = true
            val url = QuestionData.question[index].url

            val builder = Picasso.Builder(this)
            builder.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            builder.build().load(url).into(binding.imgPreview)
        } else {
            binding.radioTemplate.isChecked = true
            binding.imgPreview.setImageResource(QuestionData.question[index].imageId)
        }

        binding.btnSave.setOnClickListener {
            val radioAnswer =
                findViewById<RadioButton>(binding.radioAnswer.checkedRadioButtonId)
            val selectedImageName = items[binding.spinnerImage.selectedItemPosition]
            val imgid = this.resources.getIdentifier(selectedImageName, "drawable",
                this.packageName)
            Toast.makeText(this, "Question Updated", Toast.LENGTH_SHORT).show()
            finish()

        }


    }
}
