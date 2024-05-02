package com.example.quiz.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding
    private val navController by lazy { (requireActivity() as MainActivity).navController }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQuiz.setOnClickListener {
            navController.navigate(R.id.quizFragment)
        }

        binding.btnLeaderBoard.setOnClickListener {
            navController.navigate(R.id.btnLeaderBoard)
        }
    }

    companion object {
        fun newInstance() = LandingFragment()
    }
}