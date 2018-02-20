package com.tikalk.rabbitlambda.data.source

import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import io.reactivex.Observable

/**
 * Created by ronelg on 10/2/17.
 */
interface RabbitDataSource {

    fun getQuestionnaire(): Observable<Questionnaire>

    fun setAnswers(answers: List<Answer>): Observable<AnswersResponse>

}