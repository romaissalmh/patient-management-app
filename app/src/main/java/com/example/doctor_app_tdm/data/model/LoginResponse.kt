package com.example.doctor_app_tdm.data.model

data class LoginResponse(
        var success : Boolean,
        var token : String,
        var userId : String,
        var role:String
)
