package com.islam.domain.repo

import com.islam.domain.model.Clinic
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.model.TimeSlot
import kotlinx.coroutines.flow.Flow

interface AppointmentRepo {

    suspend fun getAllDoctors(): Flow<State<DoctorResponse?>>
    suspend fun getAllSpecialty(): Flow<State<SpecialityResponse?>?>
    suspend fun searchDcotorByName(name: String): Flow<State<DoctorResponse?>?>

    suspend fun getClinicsforDoctor(userId: String): Flow<State<List<Clinic>?>?>

    suspend fun getAvailableSlotsForDoctor(
        doctorId: String,
        date: String
    ): Flow<State<List<TimeSlot>?>?>



}