package com.islam.data.remote

import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.Patient
import com.islam.domain.model.SpecialityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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
    suspend fun addPatient(@Body patient: Patient): Response<Patient>

    @PUT("/patient/{id}")
    suspend fun updatePatient(
        @Body patient: Patient,
        @Path("id") id: String
    ): Response<Patient>



    
}