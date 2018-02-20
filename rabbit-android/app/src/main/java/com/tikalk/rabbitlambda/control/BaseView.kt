package com.tikalk.rabbitlambda.control

interface BaseView<P: BasePresenter> {

    var presenter: P?

    fun showProgress(show: Boolean)
}