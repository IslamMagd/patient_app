package com.islam.domain.repo

import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import kotlinx.coroutines.flow.Flow

interface AppointmentRepo {

    suspend fun getAllDoctors(): Flow<State<DoctorResponse?>>
    suspend fun getAllSpecialty(): Flow<State<SpecialityResponse?>?>
    suspend fun searchDcotorByName(name: String): Flow<State<DoctorResponse?>?>

}