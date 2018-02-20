package com.tikalk.rabbitlambda.form

import com.tikalk.rabbitlambda.control.BasePresenter
import com.tikalk.rabbitlambda.control.BaseView
import com.tikalk.rabbitlambda.model.Question

interface FormContract {

    interface View : BaseView<Presenter> {

        fun showQuestion(question: Question, position: Int, count: Int)

        fun showResults(correctAnswers: Int, totalQuestions: Int)
    }

    interface Presenter : BasePresenter {

        fun loadQuestionnaire(forceUpdate: Boolean)

        fun onAnswerEntered(question: Question, answer: String)

        fun onAnswerSelected(question: Question, answerIndex: Int)
    }
}