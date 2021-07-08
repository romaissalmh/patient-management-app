package com.example.doctor_app_tdm.ui.bookingDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.doctor_app_tdm.R
import com.example.doctor_app_tdm.ui.viewModel.BookingDetailsVM
import kotlinx.android.synthetic.main.bookings_details_fragment.*

class BookingsDetails : Fragment() {
    private val TAG : String ="_BookingDetailsFragment"

    companion object {
        fun newInstance() = BookingsDetails()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bookings_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val bookingViewModel = ViewModelProvider(requireActivity()).get(BookingDetailsVM::class.java)
        nomPatient.text = bookingViewModel.firstNamePatient + " " + bookingViewModel.lastNamePatient
        dateBooking.text = bookingViewModel.bookingDate
        timeBooking.text = bookingViewModel.bookingTime
        phonePatient.text = bookingViewModel.phonePatient
        address.text = bookingViewModel.address
        emailPatient.text = bookingViewModel.email
        sexePatient.text = "Sexe: "+ bookingViewModel.genderPatient
        DOBPatient.text = "DOB: "+ bookingViewModel.birthDate
        taillePatient.text = "Taille: " + bookingViewModel.heightPatient.toString()
        poidsPatient.text = "Poids: " + bookingViewModel.weightPatient.toString()
        maladiePatient.text = "Antecedents: " + bookingViewModel.personalDiseasePatient
        bloodTypePatient.text = "Groupe: " + bookingViewModel.bloodTypePatient
        backButton.setOnClickListener{
            this.findNavController().navigate(R.id.action_DetailsBookingFragment_pop)
        }
    }


}