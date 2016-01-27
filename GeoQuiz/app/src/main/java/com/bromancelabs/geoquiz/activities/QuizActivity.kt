package com.bromancelabs.geoquiz.activities;

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bromancelabs.geoquiz.R
import com.bromancelabs.geoquiz.models.Question
import com.bromancelabs.geoquiz.utils.SnackBarUtils
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity() : AppCompatActivity() {
    companion object {
        val KEY_INDEX = "index"
        val REQUEST_CODE_CHEAT = 0
    }

    val questionBank: Array<Question> = arrayOf(
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    var questionIndex = 0
    var isCheater = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz);

        log("onCreate(Bundle) called")

        questionIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        updateQuestion()

        btn_cheat.setOnClickListener { cheatButtonClicked() }
        btn_next.setOnClickListener { nextButtonClicked() }
        btn_true.setOnClickListener { trueButtonClicked() }
        btn_false.setOnClickListener { falseButtonClicked() }
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
        outState.putInt(KEY_INDEX, questionIndex)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            isCheater = if (requestCode == REQUEST_CODE_CHEAT) CheatActivity.wasAnswerShown(data) else return
        }
    }

    fun cheatButtonClicked() {
        val answerIsTrue = questionBank[questionIndex].answerTrue
        startActivityForResult(CheatActivity.newIntent(this, answerIsTrue), REQUEST_CODE_CHEAT)
    }

    fun nextButtonClicked() {
        questionIndex = (questionIndex + 1) % questionBank.size
        isCheater = false
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

        if (isCheater) {
            messageResId = R.string.judgment_snackbar
            snackBarBackgroundColor = R.color.red
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_snackbar
                snackBarBackgroundColor = R.color.green
            } else {
                messageResId = R.string.incorrect_snackbar
                snackBarBackgroundColor = R.color.red
            }
        }

        SnackBarUtils.showStyledBar(this, messageResId, R.color.white, snackBarBackgroundColor)
    }

    fun log(message: String) {
        Log.d("KTQuizActivity", message)
    }
}