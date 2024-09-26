package com.ubaya.kpd_160422124

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.kpd_160422124.databinding.QuestionCardBinding

class QuestionAdapter(): RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    class QuestionViewHolder(val binding: QuestionCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return QuestionViewHolder(binding)

    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.binding.imgQuestion.setImageResource(QuestionData.question[position].imageId)
        holder.binding.txtQuestionTitle.text = QuestionData.question[position].question

    }

    override fun getItemCount(): Int {
        return QuestionData.question.size
    }

}