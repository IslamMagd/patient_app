package com.islam.domain.usecase.PatientUseCase

import com.islam.domain.model.Patient
import com.islam.domain.repo.PatientRepo
import javax.inject.Inject

class UpdatePatientUseCase @Inject constructor(private val repository: PatientRepo) {
    suspend operator fun invoke(patient: Patient,id: String) = repository.updatePatient(patient,id)
}