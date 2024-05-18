package com.islam.data.remote

import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.Patient
import com.islam.domain.model.SpecialityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {

    @GET("/speciality")
    suspend fun getAllSpecialty(): Response<SpecialityResponse>

    @GET("/doctor")
    suspend fun getAllDoctors(): Response<DoctorResponse>

    @GET("/doctor")
    suspend fun searchDoctorByName(
        @Query("name")
        name: String
    ):Response<DoctorResponse>

    @POST("/patient")
    suspend fun createPatient(@Body patient: Patient): Response<Patient>
}