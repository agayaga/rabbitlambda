package com.tikalk.rabbitlambda.model

import com.google.gson.annotations.SerializedName

/**
 * @author moshe on 2018/02/20.
 */
data class Answer(@SerializedName("id") var id: Long,
                  @SerializedName("answer") var answer: String,
                  @SerializedName("timer") var timer: Long)
