package com.tikalk.rabbitlambda.net

import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.QuestionsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author moshe on 2018/02/20.
 */
interface RabbitService {

    @GET("db")
    fun getQuestionnaire(): Observable<QuestionsResponse>

    @POST("submit")
    fun setAnswers(): Observable<AnswersResponse>

}