package com.islam.patient.di

import com.islam.domain.repo.AuthenticationRepo
import com.islam.domain.usecase.AuthenticationUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Provides
    fun provideAuthenticationUsecase(authenticationRepo: AuthenticationRepo):AuthenticationUsecase{
        return AuthenticationUsecase(authenticationRepo)
    }
}