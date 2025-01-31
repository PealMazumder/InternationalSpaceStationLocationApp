package com.peal.spacestationapp.di

import com.peal.spacestationapp.data.location.LocationTrackerImpl
import com.peal.spacestationapp.data.repositoryImpl.IssRepositoryImpl
import com.peal.spacestationapp.domain.location.LocationTracker
import com.peal.spacestationapp.domain.repository.IssRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class IssRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindIssRepository(issRepositoryImpl: IssRepositoryImpl): IssRepository

    @Binds
    @Singleton
    abstract fun bindLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}
