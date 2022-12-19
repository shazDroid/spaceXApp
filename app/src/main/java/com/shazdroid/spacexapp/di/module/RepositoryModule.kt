package com.shazdroid.spacexapp.di.module

import com.shazdroid.spacexapp.network.ApiInterface
import com.shazdroid.spacexapp.repository.RocketRepository
import com.shazdroid.spacexapp.room.RocketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(apiInterface: ApiInterface,rocketDao: RocketDao) : RocketRepository {
        return RocketRepository(apiInterface, rocketDao)
    }
}