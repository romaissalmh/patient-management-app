package com.example.doctor_app_tdm.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor_app_tdm.data.model.Booking
import com.example.doctor_app_tdm.ui.viewModel.BookingVM
import android.widget.*
import com.example.doctor_app_tdm.R
import com.google.zxing.integration.android.IntentIntegrator


class BookingAdapter (
        val context: Context,
        var data: List<Booking>,
        var vm: BookingVM,
    ): RecyclerView.Adapter<MyViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_layout, parent, false))

        }

        override fun getItemCount() = data.size

        @SuppressLint("WrongConstant")
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bookingDate.text = data[position].bookingDate
            holder.bookingTime.text = "à "+data[position].bookingTime
            holder.scanButton.setOnClickListener {
                vm.bookingTime = data[position].bookingTime
                vm.bookingDate = data[position].bookingDate
                //open the scan qr fragment



            }

        }





}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val bookingDate = view.findViewById(R.id.bookingDate) as TextView
    val bookingTime = view.findViewById(R.id.bookingTime) as TextView
    val scanButton = view.findViewById(R.id.scanButton) as Button

}