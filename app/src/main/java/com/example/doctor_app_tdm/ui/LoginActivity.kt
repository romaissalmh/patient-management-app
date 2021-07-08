package com.example.doctor_app_tdm.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.doctor_app_tdm.MainActivity
import com.example.doctor_app_tdm.R
import com.example.doctor_app_tdm.data.repositories.LoginRepository
import com.example.doctor_app_tdm.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        start()

        button_login.setOnClickListener {

            val phone = editTextPhone.text.toString()
            val password = editTextTextPassword.text.toString()

            if (phone.isEmpty()) {
                editTextPhone.error = "Email Required"
                editTextPhone.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                editTextTextPassword.error = "Password Required"
                editTextTextPassword.requestFocus()
                return@setOnClickListener
            }

            var loginActivity = LoginRepository.Companion
            val resp = loginActivity.login(this, phone, password)
            Log.i("LoginActivity",resp.toString())
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)

        }

    }

    fun start() {
        val sharedPref = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        val con = sharedPref.getBoolean("connected",false)
        if (con){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}