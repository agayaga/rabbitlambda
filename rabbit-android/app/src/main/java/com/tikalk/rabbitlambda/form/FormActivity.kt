package com.tikalk.rabbitlambda.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tikalk.rabbitlambda.R
import com.tikalk.rabbitlambda.data.source.RabbitRepository
import com.tikalk.rabbitlambda.inject.components.DaggerApplicationComponent
import com.tikalk.rabbitlambda.model.Question
import com.tikalk.rabbitlambda.model.Questionnaire
import kotlinx.android.synthetic.main.activity_form.*
import javax.inject.Inject

class FormActivity : AppCompatActivity(), FormContract.View {

    @Inject
    lateinit var repository: RabbitRepository

    override var presenter: FormContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.create().inject(this)
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

    override fun showQuestionnaire(data: Questionnaire) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showQuestion(question: Question, position: Int, count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
