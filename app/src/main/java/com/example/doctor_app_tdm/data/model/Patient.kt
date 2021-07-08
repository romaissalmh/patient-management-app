package com.example.doctor_app_tdm.data.model

import java.sql.Date
import java.sql.Time

data class Patient(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val birthDate: String,
    val address: String,
    val phone: String,
    val gender: String,
    val email: String,
    val bloodType:String,
    val weight: Double,
    val height: Double,
    val personalDisease: String,
)
