package com.shazdroid.spacexapp.di.module

import android.content.Context
import com.shazdroid.spacexapp.room.AppDatabase
import com.shazdroid.spacexapp.room.RocketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesRocketDao(appDatabase: AppDatabase) : RocketDao {
        return appDatabase.getRocketDao()
    }
    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }
}