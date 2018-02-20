package com.tikalk.rabbitlambda.form

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tikalk.rabbitlambda.R
import com.tikalk.rabbitlambda.data.source.RabbitRepository
import com.tikalk.rabbitlambda.inject.components.DaggerApplicationComponent
import com.tikalk.rabbitlambda.inject.modules.ApplicationModule
import com.tikalk.rabbitlambda.model.Question
import com.tikalk.rabbitlambda.model.QuestionType
import kotlinx.android.synthetic.main.activity_form.*
import javax.inject.Inject

class FormActivity : AppCompatActivity(), FormContract.View {

    @Inject
    lateinit var repository: RabbitRepository

    override var presenter: FormContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build().inject(this)
        setContentView(R.layout.activity_form)
        presenter = FormPresenter(repository, this)
    }

    override fun onStart() {
        super.onStart()
        presenter?.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter?.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showQuestion(question: Question, position: Int, count: Int) {
        content.removeAllViews()
        progress_steps.max = count
        progress_steps.progress = position + 1

        when (question.type) {
            QuestionType.OPEN -> populateQuestion1(question, content)
            QuestionType.SINGLE -> when (question.options.size) {
                1 -> populateQuestion1(question, content)
                2 -> populateQuestion2(question, content)
                3 -> populateQuestion3(question, content)
                else -> populateQuestion4(question, content)
            }
            else -> populateQuestion1(question, content)
        }
    }

    override fun showScore(correctAnswers: Int, totalQuestions: Int) {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_score, null)
        val correctView: TextView = view.findViewById(R.id.correct_value)
        val countView: TextView = view.findViewById(R.id.count_value)

        correctView.text = correctAnswers.toString()
        countView.text = totalQuestions.toString()

        AlertDialog.Builder(this)
                .setCancelable(true)
                .setView(view)
                .setOnDismissListener { finish() }
                .show()
    }

    private fun populateQuestion1(question: Question, view: View) {
        val v = LayoutInflater.from(this).inflate(R.layout.options_1, content, true)

        val title: TextView = v.findViewById(R.id.title)
        val edit: EditText = v.findViewById(R.id.option_1)
        val next: View = v.findViewById(R.id.next)

        title.text = question.title
        edit.text = null
        next.setOnClickListener {
            presenter?.onAnswerEntered(question, edit.text.toString())
        }
    }

    private fun populateQuestion2(question: Question, view: View) {
        val v = LayoutInflater.from(this).inflate(R.layout.options_2, content, true)

        val title: TextView = v.findViewById(R.id.title)
        val option1: Button = v.findViewById(R.id.option_1)
        val option2: Button = v.findViewById(R.id.option_2)

        title.text = question.title
        option1.text = question.options[0]
        option1.setOnClickListener {
            presenter?.onAnswerSelected(question, 0)
        }
        option2.text = question.options[1]
        option2.setOnClickListener {
            presenter?.onAnswerSelected(question, 1)
        }
    }

    private fun populateQuestion3(question: Question, view: View) {
        val v = LayoutInflater.from(this).inflate(R.layout.options_3, content, true)

        val title: TextView = v.findViewById(R.id.title)
        val option1: Button = v.findViewById(R.id.option_1)
        val option2: Button = v.findViewById(R.id.option_2)
        val option3: Button = v.findViewById(R.id.option_3)

        title.text = question.title
        option1.text = question.options[0]
        option1.setOnClickListener {
            presenter?.onAnswerSelected(question, 0)
        }
        option2.text = question.options[1]
        option2.setOnClickListener {
            presenter?.onAnswerSelected(question, 1)
        }
        option3.text = question.options[2]
        option3.setOnClickListener {
            presenter?.onAnswerSelected(question, 2)
        }
    }

    private fun populateQuestion4(question: Question, view: View) {
        val v = LayoutInflater.from(this).inflate(R.layout.options_4, content, true)

        val title: TextView = v.findViewById(R.id.title)
        val option1: Button = v.findViewById(R.id.option_1)
        val option2: Button = v.findViewById(R.id.option_2)
        val option3: Button = v.findViewById(R.id.option_3)
        val option4: Button = v.findViewById(R.id.option_4)

        title.text = question.title
        option1.text = question.options[0]
        option1.setOnClickListener {
            presenter?.onAnswerSelected(question, 0)
        }
        option2.text = question.options[1]
        option2.setOnClickListener {
            presenter?.onAnswerSelected(question, 1)
        }
        option3.text = question.options[2]
        option3.setOnClickListener {
            presenter?.onAnswerSelected(question, 2)
        }
        option4.text = question.options[3]
        option4.setOnClickListener {
            presenter?.onAnswerSelected(question, 3)
        }
    }
}
