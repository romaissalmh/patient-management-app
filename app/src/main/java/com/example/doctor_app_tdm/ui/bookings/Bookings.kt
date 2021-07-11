package com.example.doctor_app_tdm.ui.bookings

import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctor_app_tdm.R
import com.example.doctor_app_tdm.ui.adapter.BookingAdapter
import com.example.doctor_app_tdm.ui.viewModel.BookingVM
import com.example.doctor_app_tdm.utils.sharedPrefFile
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.bookings_fragment.*


class Bookings : Fragment() {
    private val TAG = "_BookingsMedFragment"
    companion object {
        fun newInstance() = Bookings()
    }

    private lateinit var viewModel: BookingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bookings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val preferences = requireActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val token = preferences.getString("token", "defaultvalue")
        val userID = preferences.getString("userID", "defaultvalue")

        viewModel = ViewModelProvider(requireActivity()).get(BookingsViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        if (userID != null) {
            if (token != null) {
                viewModel.getListBookings(userID,token)
            }
        }
        viewModel.bookings.observe(viewLifecycleOwner, Observer {
            checkList()
        })

    }


    fun checkList() {
        val data = viewModel.bookings.value
        val vm = ViewModelProvider(requireActivity()).get(BookingVM::class.java)
        if(data != null){
            recyclerView.adapter = BookingAdapter(requireActivity() ,data,vm)
        }
    }
}