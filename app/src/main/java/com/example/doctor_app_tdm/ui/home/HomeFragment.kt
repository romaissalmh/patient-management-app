package com.example.doctor_app_tdm.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.doctor_app_tdm.R
import com.example.doctor_app_tdm.databinding.FragmentHomeBinding
import com.example.doctor_app_tdm.ui.viewModel.BookingDetailsVM
import com.example.doctor_app_tdm.utils.fromJson
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var bookingDetailsModel : BookingDetailsVM
    private lateinit var bookingViewModel : BookingDetailsVM
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAG : String ="_HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        scanButton.setOnClickListener(){

            // Code pour scanner le qrcode

             bookingViewModel = ViewModelProvider(requireActivity()).get(BookingDetailsVM::class.java)

            val integrator = IntentIntegrator.forSupportFragment(this@HomeFragment)

            integrator.setOrientationLocked(false)
            integrator.setPrompt("Scan QR code")
            integrator.setBeepEnabled(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)

            integrator.initiateScan()

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            if (result != null) {
                if (result.contents == null) {
                    Log.i(TAG, "Concelled")

                } else {
                    Log.i(TAG, "Scanned" + result.contents)
                    //Redirection vers le fragment des détails vehicule
                    bookingDetailsModel  = fromJson(result.contents)
                    bookingViewModel.address = bookingDetailsModel.address
                    bookingViewModel.birthDate = bookingDetailsModel.birthDate
                    bookingViewModel.bloodTypePatient = bookingDetailsModel.bloodTypePatient
                    bookingViewModel.bookingTime = bookingDetailsModel.bookingTime
                    bookingViewModel.bookingDate = bookingDetailsModel.bookingDate
                    bookingViewModel.firstNamePatient = bookingDetailsModel.firstNamePatient
                    bookingViewModel.lastNamePatient = bookingDetailsModel.lastNamePatient
                    bookingViewModel.email = bookingDetailsModel.email
                    bookingViewModel.heightPatient = bookingDetailsModel.heightPatient
                    bookingViewModel.weightPatient = bookingDetailsModel.weightPatient
                    bookingViewModel.personalDiseasePatient = bookingDetailsModel.personalDiseasePatient
                    bookingViewModel.genderPatient = bookingViewModel.genderPatient
                    bookingViewModel.phonePatient = bookingViewModel.phonePatient
                    findNavController().navigate(R.id.action_navigation_home_to_bookingsDetails)

                }

            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}