package com.tikalk.rabbitlambda.model

import com.google.gson.annotations.SerializedName

/**
 * @author moshe on 2018/02/20.
 */
data class Question(@SerializedName("id") var id: Long,
                    @SerializedName("type") var type: QuestionType,
                    @SerializedName("q") var title: String,
                    @SerializedName("answers") var options: List<String>,
                    @SerializedName("correct_answer") var answer: Int)

enum class QuestionType {
    @SerializedName("open")
    OPEN,
    @SerializedName("single")
    SINGLE,
    @SerializedName("multiple")
    MULTIPLE
}