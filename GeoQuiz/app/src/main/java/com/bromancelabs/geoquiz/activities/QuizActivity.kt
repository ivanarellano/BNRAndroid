package com.bromancelabs.geoquiz.activities;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bromancelabs.geoquiz.R
import com.bromancelabs.geoquiz.models.Question
import com.bromancelabs.geoquiz.utils.SnackBarUtils

import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity(val keyIndex: String = "index") : AppCompatActivity() {

    val questionBank: Array<Question> = arrayOf(
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    var questionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz);

        log("onCreate(Bundle) called")

        questionIndex = savedInstanceState?.getInt(keyIndex, 0) ?: 0

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        log("onStart() called");
    }

    override fun onPause() {
        super.onPause()
        log("onPause() called");
    }

    override fun onResume() {
        super.onResume()
        log("onResume() called");
    }

    override fun onStop() {
        super.onStop()
        log("onStop() called");
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy() called");
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState() called");
        outState.putInt(keyIndex, questionIndex)
    }

    fun nextButtonClicked() {
        questionIndex = (questionIndex + 1) % questionBank.size
        updateQuestion()
    }

    fun trueButtonClicked() {
        checkAnswer(true)
    }

    fun falseButtonClicked() {
        checkAnswer(false)
    }

    fun updateQuestion() {
        val question = questionBank[questionIndex].textResId
        tv_question.setText(question)
    }

    fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = questionBank[questionIndex].answerTrue
        val messageResId: Int
        val snackBarBackgroundColor: Int

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_snackbar;
            snackBarBackgroundColor = R.color.green;
        } else {
            messageResId = R.string.incorrect_snackbar;
            snackBarBackgroundColor = R.color.red;
        }

        SnackBarUtils.showStyledBar(this, messageResId, R.color.white, snackBarBackgroundColor);
    }

    fun log(message: String) {
        Log.d("KTQuizActivity", message)
    }
}