package com.example.quiz.quiz.model


import com.google.gson.annotations.SerializedName

data class QuestionItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("choices")
    val choices: List<String>,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("question")
    val question: String,

    var answer: String? = null
)