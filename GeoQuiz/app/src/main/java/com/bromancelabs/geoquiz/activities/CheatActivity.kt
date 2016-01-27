package com.bromancelabs.geoquiz.activities;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.bromancelabs.geoquiz.R
import com.bromancelabs.geoquiz.utils.onClick
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {
    companion object {
        val EXTRA_ANSWER_IS_TRUE = "com.bromancelabs.geoquiz.activities.answer_is_true"
        val EXTRA_ANSWER_SHOWN = "com.bromancelabs.geoquiz.activities.answer_shown"

        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            var intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

        fun wasAnswerShown(result: Intent?): Boolean {
            return result?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        btn_showAnswer.onClick { showAnswerClicked(tv_answer) }
    }

    fun showAnswerClicked(view: TextView) {
        view.setText(if (answerIsTrue) R.string.true_button else R.string.false_button)
        setAnswerShownResult(true)
    }

    fun setAnswerShownResult(isAnswerShown: Boolean) {
        var intent = Intent()
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(RESULT_OK, intent)
    }
}