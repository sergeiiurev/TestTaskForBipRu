package com.example.testtaskforbipru.presentationLayer.finalResultActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testtaskforbipru.R
import com.example.testtaskforbipru.databinding.ActivityFinalResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalResultActivity : AppCompatActivity() {
    private val vm: FinalResultVM by viewModels()
    private val binding by lazy {ActivityFinalResultBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        vm.getVehicleRegInfoEnteringIsSkippedState()
        vm.vehicleRegInfoEnteringIsSkippedStateLiveData.observe(this){vehicleRegInfoEnteringIsSkipped: Boolean->
            if(!vehicleRegInfoEnteringIsSkipped){
                vm.enteredIdentifierNumberLiveData.observe(this){
                    binding.identifierResultTextView.text = getString(R.string.final_activity_identifier_value, it)
                }
                vm.enteredCertificateNumberLiveData.observe(this){
                    binding.certificateResultTextView.text = getString(R.string.final_activity_certificate_value, it)
                }
            }
        }
        vm.enteredDrivingLicenceCorrectnessLiveData.observe(this){
            if(it == ""){
                binding.drivingLicenceResultTextView.text = getString(R.string.final_driving_licence_tv_default)
            } else {
                binding.drivingLicenceResultTextView.text = getString(R.string.final_activity_driving_licence_value, it)
            }
        }
        vm.getAllEnteredData()
    }
}