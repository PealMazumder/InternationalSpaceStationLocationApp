package com.peal.spacestationapp.di

import com.peal.spacestationapp.data.repositoryImpl.LocationRepositoryImpl
import com.peal.spacestationapp.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Peal Mazumder on 31/1/25.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository
}
