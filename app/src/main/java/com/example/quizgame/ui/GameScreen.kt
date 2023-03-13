package com.example.quizgame.ui.theme

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    /*gameView: GameViewModel = viewModel()*/
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(
            countQuestion = gameUiState.countQuestion,
            score = gameUiState.score
        )
        GameLayout(
            /*onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            userGuess = gameViewModel.userGuess,
            onKeyboardDone = { gameViewModel.checkUserGuess() },*/
            currentQuestion = gameUiState.currentQuestion,
            isChoiceTrue = gameUiState.isChoiceTrue,
            userAns = gameUiState.userAns
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                onClick = { gameViewModel.checkUserGuess() }
            ) {
                Text(stringResource(R.string.next))
            }
        }
        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}


@Composable
fun Gamestatus(questionCount: Int, score: Int, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(R.string.question_count, questionCount),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(R.string.score, score),
            fontSize = 18.sp,
        )
    }
}
@Composable
fun GameLayout(
    currentQuestion: String,
    isChoiceTrue: Boolean,
    userAns: String,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp), modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = currentQuestion,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
            
        )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "a")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "b")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "c")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "d")
        }
    }
}

@Composable
fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}