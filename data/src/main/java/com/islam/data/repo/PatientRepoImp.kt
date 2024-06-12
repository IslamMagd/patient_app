package com.islam.data.repo

import com.islam.data.remote.ServiceApi
import com.islam.domain.model.Patient
import com.islam.domain.model.State
import com.islam.domain.repo.PatientRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PatientRepoImp @Inject constructor(private val serviceApi: ServiceApi): PatientRepo{
    override suspend fun addPatient(patient: Patient): Flow<State<Patient?>?> {
        return flow{
            emit(State.Loading)
            val result = serviceApi.addPatient(patient)
            if(result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }

    override suspend fun updatePatient(patient: Patient, id: String): Flow<State<Patient?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.updatePatient(patient,id)
            if(result.isSuccessful){
                emit((State.Success(result.body())))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }
}