package com.peal.spacestationapp.di

import com.peal.spacestationapp.data.repositoryImpl.AuthenticationRepositoryImpl
import com.peal.spacestationapp.domain.repository.AuthenticationRepository
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
abstract class AuthenticationModule {
    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository
}
