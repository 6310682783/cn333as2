package com.example.quizgame.ui

data class GameUiState(
    val currentQuestion: String = "",
    val countQuestion: Int = 1,
    val score: Int = 0,
    val correctAns: String = "",
    val userAns: String = "",
    val isGameOver: Boolean = false,
    val isChoiceTrue: Boolean = true,
)
