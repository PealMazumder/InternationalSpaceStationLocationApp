package com.peal.spacestationapp.di

import android.content.Context
import com.peal.spacestationapp.data.remote.IssApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Module
@InstallIn(SingletonComponent::class)
object IssAppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IssApiService {
        return retrofit.create(IssApiService::class.java)
    }
}