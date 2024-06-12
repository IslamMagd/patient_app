package com.islam.domain.repo

import com.islam.domain.model.Patient
import com.islam.domain.model.State
import kotlinx.coroutines.flow.Flow

interface PatientRepo {
    suspend fun addPatient(patient: Patient): Flow<State<Patient?>?>
    suspend fun updatePatient(patient: Patient,id: String): Flow<State<Patient?>?>
}