package com.example.doctor_app_tdm.ui.viewModel

import androidx.lifecycle.ViewModel

class BookingDetailsVM: ViewModel() {
    var bookingDate : String = ""
    var bookingTime : String = ""
    var idDoctor : String = ""
    var idPatient : String = ""
    var firstNamePatient : String = ""
    var lastNamePatient: String = ""
    var phonePatient: String = ""
    var genderPatient : String = ""
    var address : String = ""
    var birthDate : String = ""
    var email:String = ""
    var weightPatient: Float = 0f
    var heightPatient: Float = 0f
    var bloodTypePatient: String = ""
    var personalDiseasePatient: String = ""
}