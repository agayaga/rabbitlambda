package com.tikalk.rabbitlambda.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.tikalk.rabbitlambda.R
import com.tikalk.rabbitlambda.data.source.RabbitDataSource
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Questionnaire
import com.tikalk.rabbitlambda.model.QuestionsResponse
import io.reactivex.Observable
import java.io.InputStreamReader

class RabbitLocalDataSource(private val context: Context) : RabbitDataSource {

    override fun getQuestionnaire(): Observable<Questionnaire> {
        val raw = context.resources.openRawResource(R.raw.db)
        val reader = InputStreamReader(raw)
        val response: QuestionsResponse = Gson().fromJson(reader, QuestionsResponse::class.java)
        reader.close()
        return Observable.just(response.body)
    }

    override fun setAnswers(answers: List<Answer>): Observable<AnswersResponse> {
        return Observable.empty()
    }
}