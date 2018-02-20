package com.tikalk.rabbitlambda.inject.modules

import android.content.Context
import com.tikalk.rabbitlambda.data.source.RabbitRepository
import com.tikalk.rabbitlambda.data.source.local.RabbitLocalDataSource
import com.tikalk.rabbitlambda.data.source.remote.RabbitRemoteDataSource
import com.tikalk.rabbitlambda.net.RabbitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {
    @Singleton
    @Provides
    fun provideRemoteDataSource(service: RabbitService): RabbitRemoteDataSource {
        return RabbitRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(): RabbitLocalDataSource {
        return RabbitLocalDataSource(context)
    }

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RabbitRemoteDataSource,
                          localDataSource: RabbitLocalDataSource): RabbitRepository {
        return RabbitRepository(remoteDataSource, localDataSource)
    }
}