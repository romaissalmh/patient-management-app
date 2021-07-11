package com.example.doctor_app_tdm.data.api

import com.example.doctor_app_tdm.data.model.Booking
import com.example.doctor_app_tdm.data.model.LoginBody
import com.example.doctor_app_tdm.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ServiceProvider {
    @GET("api/bookings/byDoctor/{id}")
    fun getBookingsByDoctor(@Path("id") id:String? ,@Header("authorization") token: String): Call<List<Booking>>

    @POST("api/doctors/login")
    fun loginDoctor(  @Body info: LoginBody ): Call<LoginResponse>

}