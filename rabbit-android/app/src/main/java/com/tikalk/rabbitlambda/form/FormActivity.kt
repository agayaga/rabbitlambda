package com.tikalk.rabbitlambda.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
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

        val layoutId = when (question.type) {
            QuestionType.OPEN -> R.layout.options_1
            QuestionType.SINGLE -> when (question.options.size) {
                1 -> R.layout.options_1
                2 -> R.layout.options_2
                3 -> R.layout.options_3
                else -> R.layout.options_4
            }
            else -> R.layout.options_1
        }
        val view = LayoutInflater.from(this).inflate(layoutId, content, true)
        populateQuestion(question, view)
    }

    override fun showResults(correctAnswers: Int, totalQuestions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun populateQuestion(question: Question, view: View) {
        //TODO
    }
}
