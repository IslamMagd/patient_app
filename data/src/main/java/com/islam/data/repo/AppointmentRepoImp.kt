package com.islam.data.repo

import com.islam.data.remote.ServiceApi
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.repo.AppointmentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppointmentRepoImp @Inject constructor(private val serviceApi: ServiceApi): AppointmentRepo {
    override suspend fun getAllDoctors(): Flow<State<DoctorResponse?>> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.getAllDoctors()
            if(result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }

    override suspend fun getAllSpecialty(): Flow<State<SpecialityResponse?>?> {
        return flow {
           emit(State.Loading)
            val result = serviceApi.getAllSpecialty()
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }

        }
    }

    override suspend fun searchDcotorByName(name: String): Flow<State<DoctorResponse?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.searchDoctorByName(name)
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }


}