package com.example.doctor_app_tdm.ui.bookings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctor_app_tdm.data.model.Booking
import com.example.doctor_app_tdm.data.repositories.BookingRepository

class BookingsViewModel : ViewModel() {
    private val TAG = "TAG-BookingsDisplayViewModel"
    var bookings=  MutableLiveData<ArrayList<Booking>>()
    fun  getListBookings(id:String)  {
        BookingRepository.getBookingsByDoctor(TAG,id) {
            Log.i(TAG, "view model here")
            bookings.value = it?.value
        }
    }
}