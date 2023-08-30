package com.example.testtaskforbipru.presentationLayer.finalResultActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.SaveDataEnteringEndingStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.GetDrivingLicenceNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.GetVehicleRegInfoEnteringIsSkippedStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.GetVehicleRegCertificateNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.GetVehicleRegIdentifierNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinalResultVM @Inject constructor(private val getIdentifierNumberUC: GetVehicleRegIdentifierNumberUseCase,
                                        private val getCertificateNumberUC: GetVehicleRegCertificateNumberUseCase,
                                        private val getDrivingLicenceNumberUC: GetDrivingLicenceNumberUseCase,
                                        saveDataEnteringCompletenessUC: SaveDataEnteringEndingStateUseCase,
                                        private val getVehicleRegInfoEnteringIsSkippedStateUC: GetVehicleRegInfoEnteringIsSkippedStateUseCase
): ViewModel() {
    init {
        saveDataEnteringCompletenessUC.execute(
            com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateToSave(
                state = true
            )
        )
    }
    private var _enteredIdentifierNumberLiveData = MutableLiveData<String>()
    val enteredIdentifierNumberLiveData = _enteredIdentifierNumberLiveData as LiveData<String>
    private var _enteredCertificateNumberLiveData = MutableLiveData<String>()
    val enteredCertificateNumberLiveData = _enteredCertificateNumberLiveData as LiveData<String>
    private var _enteredDrivingLicenceCorrectnessLiveData = MutableLiveData<String>()
    val enteredDrivingLicenceCorrectnessLiveData = _enteredDrivingLicenceCorrectnessLiveData as LiveData<String>
    private var _vehicleRegInfoEnteringIsSkippedStateLiveData = MutableLiveData<Boolean>()
    val vehicleRegInfoEnteringIsSkippedStateLiveData = _vehicleRegInfoEnteringIsSkippedStateLiveData as LiveData<Boolean>

    fun getAllEnteredData(){
        _enteredIdentifierNumberLiveData.value = getIdentifierNumberUC.execute().identifierNumberValue
        _enteredCertificateNumberLiveData.value = getCertificateNumberUC.execute().certificateNumberValue
        _enteredDrivingLicenceCorrectnessLiveData.value = getDrivingLicenceNumberUC.execute().licenceNumberValue ?: ""
    }

    fun getVehicleRegInfoEnteringIsSkippedState(){
        _vehicleRegInfoEnteringIsSkippedStateLiveData.value = getVehicleRegInfoEnteringIsSkippedStateUC.execute().state
    }
}