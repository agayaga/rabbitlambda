package com.tikalk.rabbitlambda.data.source.remote

import com.tikalk.rabbitlambda.data.source.RabbitDataSource
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import com.tikalk.rabbitlambda.net.RabbitService
import io.reactivex.Observable

class RabbitRemoteDataSource(private val service: RabbitService) : RabbitDataSource {

    override fun getQuestionnaire(): Observable<Questionnaire> {
        return service.getQuestionnaire()
                .map { Questionnaire(it) }
    }

    override fun setAnswers(answers: List<Answer>): Observable<AnswersResponse> {
        return service.setAnswers(answers)
    }
}