package com.example.doctor_app_tdm.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.doctor_app_tdm.data.api.ServiceBuilder
import com.example.doctor_app_tdm.data.api.ServiceProvider
import com.example.doctor_app_tdm.data.model.Booking
import com.example.doctor_app_tdm.utils.userToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getBookingsByDoctor(
            TAG: String,
            id:String,
            token:String,
            onResult: (MutableLiveData<ArrayList<Booking>>?) -> Unit
        ) {

            val call = api.getBookingsByDoctor(id, "Basic $token")

            call.enqueue(object : Callback<List<Booking>> {
                override fun onResponse(
                    call: Call<List<Booking>>,
                    response: Response<List<Booking>>
                ) {
                    if (!response.isSuccessful) {
                        Log.i(TAG, "CODE:" + response.code().toString())
                        onResult(null)
                    }
                    val RDVList = ArrayList<Booking>()
                    val finalList = MutableLiveData<ArrayList<Booking>>()
                    val RDVRespond = response.body()
                    if (RDVRespond != null) {
                        for (m in RDVRespond!!) {
                            RDVList.add(m)
                            Log.i(TAG,m.bookingDate)

                        }
                        finalList.value = RDVList
                        onResult(finalList)
                    }
                }

                override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                    Log.i(TAG, "error CODE:" + t.message)
                    onResult(null)
                }
            })

        }
    }
}