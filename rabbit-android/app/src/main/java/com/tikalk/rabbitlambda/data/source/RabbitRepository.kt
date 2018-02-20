package com.tikalk.rabbitlambda.data.source

import com.tikalk.rabbitlambda.data.source.local.RabbitLocalDataSource
import com.tikalk.rabbitlambda.data.source.remote.RabbitRemoteDataSource
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import io.reactivex.Observable

class RabbitRepository(private val remoteDataSource: RabbitRemoteDataSource,
                       private val localDataSource: RabbitLocalDataSource) : RabbitDataSource {

    override fun getQuestionnaire(): Observable<Questionnaire> {
        return remoteDataSource.getQuestionnaire()
    }

    override fun setAnswers(answers: List<Answer>): Observable<AnswersResponse> {
        return remoteDataSource.setAnswers(answers)
    }
}