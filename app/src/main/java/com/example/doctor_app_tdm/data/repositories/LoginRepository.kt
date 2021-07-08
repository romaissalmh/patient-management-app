package com.example.doctor_app_tdm.data.repositories

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.doctor_app_tdm.MainActivity
import com.example.doctor_app_tdm.data.api.ServiceBuilder
import com.example.doctor_app_tdm.data.api.ServiceProvider
import com.example.doctor_app_tdm.data.model.LoginBody
import com.example.doctor_app_tdm.data.model.LoginResponse
import com.example.doctor_app_tdm.utils.sharedPrefFile
import com.example.doctor_app_tdm.utils.userToken
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.auth0.android.jwt.JWT
import com.example.doctor_app_tdm.utils.idUser
import com.example.doctor_app_tdm.utils.role

class LoginRepository {
    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        fun login(
            context: Context,
            phone: String,
            password: String
        ) {

            var loginBody = LoginBody(phone, password)
            val authDoctorRequest = api.loginDoctor(loginBody) // consommation de l'api

            val sharedPref = context.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )

            authDoctorRequest.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: LoginResponse = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            LoginResponse::class.java
                        )
                        Toast.makeText(context, "Erreur dans le login", Toast.LENGTH_LONG).show()
                        Log.i("LoginRepo",response.code().toString())
                    } else {
                        val resp = response.body()

                        if (resp != null) {
                            userToken = resp?.token.toString()
                            idUser = resp.userId
                            role = resp.role
                            var jwt = JWT(userToken)
                            var claimID = jwt.getClaim("id") //claimID to have the connected user's ID
                            var claimRole = jwt.getClaim("role")
                            Log.i("LoginRepo", claimID.toString())
                            with(sharedPref?.edit()) {
                                this?.putString("userID", claimID.asString())
                                this?.putString("userRole", claimRole.asString())
                                this?.putString("token", userToken)
                                this?.putBoolean("connected", true)
                                this?.apply()
                            }

                        }

                        Toast.makeText(context, "Connexion établie", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(context, MainActivity::class.java)
                        context.startActivity(myIntent)
                        /*get DATA example : (in other activities)
                        * val preferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                        * val userID = preferences.getString("userID", "Default")
                        */

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}