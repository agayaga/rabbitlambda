package com.tikalk.rabbitlambda.form

import com.tikalk.rabbitlambda.control.BasePresenter
import com.tikalk.rabbitlambda.control.BaseView
import com.tikalk.rabbitlambda.model.Question
import com.tikalk.rabbitlambda.model.Questionnaire

interface FormContract {

    interface View : BaseView<Presenter> {

        fun showQuestionnaire(data: Questionnaire)
    }

    interface Presenter : BasePresenter {

        fun loadQuestionnaire(forceUpdate: Boolean)

        fun onAnswerEntered(question: Question, answer: String)

        fun onAnswerSelected(question: Question, answerIndex: Int)
    }
}