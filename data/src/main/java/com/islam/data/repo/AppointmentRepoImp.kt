package com.islam.data.repo

import android.media.Rating
import android.util.Log
import com.islam.data.remote.ServiceApi
import com.islam.domain.model.BookingInput
import com.islam.domain.model.Clinic
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.model.TimeSlot
import com.islam.domain.repo.AppointmentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppointmentRepoImp @Inject constructor(private val serviceApi: ServiceApi): AppointmentRepo {
    override suspend fun getAllDoctors(token: String): Flow<State<DoctorResponse?>> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.getAllDoctors("Bearer $token")
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

    override suspend fun getClinicsforDoctor(userId: String): Flow<State<List<Clinic>?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.getClinicsForDoctor(userId)
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }

    override suspend fun getAvailableSlotsForDoctor(
        doctorId: String,
        date: String
    ): Flow<State<List<TimeSlot>?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.getAvailableSlotsForDoctor(doctorId,date)
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }

    override suspend fun rateDoctor(doctorId: String, rate: Int): Flow<State<Rating?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.rateDoctor(doctorId,rate)
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }

    override suspend fun getAverageRating(
        doctorId: String
    ): Double = serviceApi.getAverageRating(doctorId)

    override suspend fun addBooking(bookingInput: BookingInput): Flow<State<BookingInput?>?> {
        return flow {
            emit(State.Loading)
            val result = serviceApi.addBooking(bookingInput)
            if (result.isSuccessful){
                emit(State.Success(result.body()))
            }
            else{
                emit(State.Error(result.message()))
            }
        }
    }


}