package com.example.doctor_app_tdm.utils


import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import com.example.doctor_app_tdm.R
import com.example.doctor_app_tdm.ui.viewModel.BookingDetailsVM
import com.google.gson.Gson


var userToken :String =""
val sharedPrefFile: String = "kotlinsharedpreference"
var idTokenUser :String = ""
var idUser : String = ""
var role : String = ""

        //méthode pour show and hide passwords
         fun showPassword(a: EditText, b: ImageView, isShow: Boolean) {
            if (isShow) {
                // To show the password
                a.transformationMethod = HideReturnsTransformationMethod.getInstance()
                b.setImageResource(R.drawable.ic_hide_passsword)
            } else {
                // To hide the password
                a.transformationMethod = PasswordTransformationMethod.getInstance()
                b.setImageResource(R.drawable.ic_show_password)
            }
            // This line of code to put the pointer at the end of the password string
            a.setSelection(a.text.toString().length)
        }

      fun fromJson(jsonString : String): BookingDetailsVM {
          return Gson().fromJson(jsonString, BookingDetailsVM::class.java)

      }