package com.tikalk.rabbitlambda.form

import android.util.Log
import com.tikalk.rabbitlambda.data.source.RabbitDataSource
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.Question
import com.tikalk.rabbitlambda.model.Questionnaire
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author moshe on 2018/02/20.
 */
class FormPresenter(private val repository: RabbitDataSource,
                    private val view: FormContract.View)
    : FormContract.Presenter {

    private val TAG = "FormPresenter"

    init {
        view.presenter = this
    }

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var data: Questionnaire? = null
    private var itemIndex: Int = 0
    private val answers = ArrayList<Answer>()

    override fun subscribe() {
        loadQuestionnaire(false)
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun loadQuestionnaire(forceUpdate: Boolean) {
        view.showProgress(true)

        val disposable = repository.getQuestionnaire()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ res ->
                    data = res
                    itemIndex = 0
                    showNextQuestion()
                    view.showProgress(false)
                }, { e ->
                    Log.e(TAG, "loadQuestionnaire error: $e")
                    view.showProgress(false)
                })

        disposables.add(disposable)
    }

    override fun onAnswerEntered(q: Question, answer: String) {
        val a = Answer(q.id, answer, 0L)
        answers.add(a)
        showNextQuestion()
    }

    override fun onAnswerSelected(q: Question, answerIndex: Int) {
        val a = Answer(q.id, q.options[answerIndex], 0L)
        answers.add(a)
        showNextQuestion()
    }

    private fun showQuestion(index: Int) {
        view.showQuestion(data!!.questions[index], index, data!!.questions.size)
    }

    private fun showNextQuestion() {
        showQuestion(itemIndex++)
    }
}