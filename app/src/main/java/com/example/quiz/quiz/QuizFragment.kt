package com.example.quiz.quiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.FragmentQuizBinding
import com.example.quiz.quiz.model.QuestionItem
import com.example.quiz.quiz.ui.QuestionRecyclerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val navController by lazy { (requireActivity() as MainActivity).navController }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionJson = resources.openRawResource(R.raw.question)
        questionJson.bufferedReader().use { it.readText() }.let {
            val questionList: List<QuestionItem> = Gson()
                .fromJson(it, object: TypeToken<List<QuestionItem>>() {}.type)

            val adapter = QuestionRecyclerAdapter(questionList.shuffled())
            binding.rvQuestion.apply {
                this.adapter = adapter
                this.layoutManager = LinearLayoutManager(context)
            }

            binding.btnSubmit.setOnClickListener {
                saveScore(adapter.summarizeScore())
                navController.navigate(R.id.leaderBoardFragment)
            }
        }
    }

    private fun saveScore(score: Int) {
        activity?.let {
            // Get a SharedPreferences object
            val sharedPreferences = it.getSharedPreferences("quiz_score", Context.MODE_PRIVATE)

            val dateTimeStamp = LocalDateTime.now().toString()
            // Set a string value
            sharedPreferences.edit().putInt(dateTimeStamp, score).apply()
        }
    }
}