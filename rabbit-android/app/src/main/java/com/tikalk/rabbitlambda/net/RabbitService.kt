package com.tikalk.rabbitlambda.net

import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author moshe on 2018/02/20.
 */
interface RabbitService {

    @GET("db")
    fun getQuestionnaire(): Observable<Questionnaire>

    @POST("submit")
    fun setAnswers(@Body answers: List<Answer>): Observable<AnswersResponse>

}