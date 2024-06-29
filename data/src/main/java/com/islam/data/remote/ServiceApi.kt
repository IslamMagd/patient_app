package com.islam.data.remote

import android.media.Rating
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.BookingInput
import com.islam.domain.model.Clinic
import com.islam.domain.model.Doctor
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.LoginRequest
import com.islam.domain.model.LoginResponse
import com.islam.domain.model.Patient
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.TimeSlot
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @POST("/auth/register/patient")
    suspend fun registerPatient(@Body patient: Patient): Response<AuthenticationResult>

    @POST("/auth/login")
    suspend fun loginWithEmailAndPassword(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/speciality")
    suspend fun getAllSpecialty(): Response<SpecialityResponse>

    @GET("/doctor")
    suspend fun getAllDoctors(
        @Header("Authentication") token: String
    ): Response<DoctorResponse>

    @GET("/doctor")
    suspend fun searchDoctorByName(
        @Query("name")
        name: String
    ):Response<DoctorResponse>

    @POST("/patient")
    suspend fun addPatient(@Body patient: Patient): Response<Patient>

    @POST("/patient")
    suspend fun updatePatient(@Body patient: Patient): Response<Patient>

    suspend fun updatePatientImage()

    @GET("/clinic")
    suspend fun getClinicsForDoctor(
        @Query("userId") userId: String
    ): Response<List<Clinic>>

    @GET("/appointment")
    suspend fun getAvailableSlotsForDoctor(
        @Query("doctorId") doctorId: String,
        @Query("dateString") dateString: String
    ): Response<List<TimeSlot>>

    @POST("/rating/{doctorId}")
    suspend fun rateDoctor(
        @Path("doctorId") doctorId: String,
        @Query("rate") rate: Int
    ): Response<Rating>

    @GET("/rating/{doctorId}")
    suspend fun getAverageRating(
        @Path("doctorId") doctorId: String
    ): Double

    @POST("/booking")
    suspend fun addBooking(
        @Body bookingInput: BookingInput
    ): Response<BookingInput>





    
}