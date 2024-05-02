package com.example.quiz.leaderboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.FragmentLeaderBoardBinding
import java.time.LocalDateTime

class LeaderBoardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderBoardBinding
    private val navController by lazy { (requireActivity() as MainActivity).navController }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeaderBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { navController.navigate(R.id.landingFragment) }
        activity?.let {
            // Get a SharedPreferences object
            val sharedPreferences = it.getSharedPreferences("quiz_score", Context.MODE_PRIVATE)

            sharedPreferences.all.forEach {
                val textView = TextView(context)
                textView.text = "${it.key} \t\t ${it.value}"

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )

                binding.llScore.addView(textView, layoutParams)
            }
        }
    }
}