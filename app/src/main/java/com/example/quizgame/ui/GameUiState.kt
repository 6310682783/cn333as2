package com.example.quizgame.ui

import com.example.quizgame.data.Question

data class GameUiState(
    val currentQuestion: Question,
    val choices: List<String>,
    val score: Int,
    val quizNumber: Int
)
