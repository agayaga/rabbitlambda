package com.tikalk.rabbitlambda.data.source.remote

import com.tikalk.rabbitlambda.data.source.RabbitDataSource
import com.tikalk.rabbitlambda.inject.components.DaggerApplicationComponent
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import com.tikalk.rabbitlambda.net.RabbitService
import io.reactivex.Observable
import javax.inject.Inject

class RabbitRemoteDataSource : RabbitDataSource {

    @Inject
    lateinit var service: RabbitService

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    override fun getQuestionnaire(): Observable<Questionnaire> {
        return service.getQuestionnaire()
    }

    override fun setAnswers(answers: List<Answer>): Observable<AnswersResponse> {
        return service.setAnswers(answers)
    }
}