package com.tikalk.rabbitlambda.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tikalk.rabbitlambda.R
import com.tikalk.rabbitlambda.data.source.RabbitDataSource
import com.tikalk.rabbitlambda.model.Answer
import com.tikalk.rabbitlambda.model.AnswersResponse
import com.tikalk.rabbitlambda.model.Question
import com.tikalk.rabbitlambda.model.Questionnaire
import io.reactivex.Observable
import java.io.InputStreamReader

class RabbitLocalDataSource(private val context: Context) : RabbitDataSource {

    override fun getQuestionnaire(): Observable<Questionnaire> {
        val raw = context.resources.openRawResource(R.raw.db)
        val reader = InputStreamReader(raw)
        val type = object : TypeToken<List<Question>>() {}.type
        val list: List<Question> = Gson().fromJson(reader, type)
        val result = Questionnaire(list)
        reader.close()
        return Observable.just(result)
    }

    override fun setAnswers(answers: List<Answer>): Observable<AnswersResponse> {
        val raw = context.resources.openRawResource(R.raw.submit)
        val reader = InputStreamReader(raw)
        val type = AnswersResponse::class.java
        val result: AnswersResponse = Gson().fromJson(reader, type)
        reader.close()
        return Observable.just(result)
    }
}