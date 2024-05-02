package com.example.quiz.quiz.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.QuestionCardBinding
import com.example.quiz.quiz.model.QuestionItem

class QuestionRecyclerAdapter(private val dataset: List<QuestionItem>) :
    RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding, this::onChecked)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.setData(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    override fun getItemViewType(position: Int): Int = position

    private fun onChecked(position: Int, answer: String) {
        dataset[position].answer = answer
    }

    fun summarizeScore(): Int = dataset.count {
        !it.answer.isNullOrBlank() && it.answer == it.correctAnswer
    }

    class QuestionViewHolder(
        private val binding: QuestionCardBinding,
        private val onChecked: ((Int, String) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(question: QuestionItem) {
            binding.tvQuestion.text = question.question

            question.choices.shuffled().forEachIndexed { index, choice ->
                when (index) {
                    0 -> binding.rbChoiceFirst
                    1 -> binding.rbChoiceSecond
                    2 -> binding.rbChoiceThird
                    else -> binding.rbChoiceFourth
                }.text = choice
            }

            binding.rbgChoices.setOnCheckedChangeListener { _, checkedId ->
                val answer = when (checkedId) {
                    binding.rbChoiceFirst.id ->  binding.rbChoiceFirst.text.toString()
                    binding.rbChoiceSecond.id ->  binding.rbChoiceSecond.text.toString()
                    binding.rbChoiceThird.id ->  binding.rbChoiceThird.text.toString()
                    binding.rbChoiceFourth.id ->  binding.rbChoiceFourth.text.toString()
                    else -> ""
                }
                onChecked.invoke(adapterPosition, answer)
            }
        }


    }
}