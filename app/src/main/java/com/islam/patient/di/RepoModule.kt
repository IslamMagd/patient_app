package com.islam.patient.di

import com.google.firebase.auth.FirebaseAuth
import com.islam.data.remote.ServiceApi
import com.islam.data.repo.AppointmentRepoImp
import com.islam.data.repo.AuthenticationRepoImp
import com.islam.domain.repo.AppointmentRepo
import com.islam.domain.repo.AuthenticationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideAuthenticationRepo(auth: FirebaseAuth): AuthenticationRepo{
    return AuthenticationRepoImp(auth)
    }

    @Provides
    fun provideAppointmentRepo(serviceApi: ServiceApi): AppointmentRepo{
        return AppointmentRepoImp(serviceApi)

    }


}