package com.tikalk.rabbitlambda.inject.components

import android.app.Application
import com.tikalk.rabbitlambda.LoginActivity
import com.tikalk.rabbitlambda.data.source.remote.RabbitRemoteDataSource
import com.tikalk.rabbitlambda.form.FormActivity
import com.tikalk.rabbitlambda.inject.modules.ApplicationModule
import com.tikalk.rabbitlambda.inject.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class))
interface ApplicationComponent {
    fun inject(app: Application)
    fun inject(source: RabbitRemoteDataSource)
    fun inject(activity: LoginActivity)
    fun inject(activity: FormActivity)
}