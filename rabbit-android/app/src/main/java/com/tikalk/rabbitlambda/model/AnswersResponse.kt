package com.tikalk.rabbitlambda.model

import com.google.gson.annotations.SerializedName

/**
 * @author moshe on 2018/02/20.
 */
data class AnswersResponse(@SerializedName("total_questions") var totalQuestions: Int,
                           @SerializedName("correct_answers") var correctAnswers: Int)