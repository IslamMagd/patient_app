package com.islam.patient.di

import com.google.firebase.auth.FirebaseAuth
import com.islam.data.remote.ServiceApi
import com.islam.data.repo.AppointmentRepoImp
import com.islam.data.repo.AuthenticationRepoImp
import com.islam.data.repo.PatientRepoImp
import com.islam.domain.repo.AppointmentRepo
import com.islam.domain.repo.AuthenticationRepo
import com.islam.domain.repo.PatientRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideAuthenticationRepo(auth: FirebaseAuth,serviceApi: ServiceApi): AuthenticationRepo{
    return AuthenticationRepoImp(auth,serviceApi)
    }

    @Provides
    fun provideAppointmentRepo(serviceApi: ServiceApi): AppointmentRepo{
        return AppointmentRepoImp(serviceApi)

    }

    @Provides
    fun providePatientRepo(serviceApi: ServiceApi): PatientRepo{
        return PatientRepoImp(serviceApi)
    }


}