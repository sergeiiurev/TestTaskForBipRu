package com.example.testtaskforbipru.presentationLayer.regCertificateActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskforbipru.domainLayer.usecases.utilities.TranslateEnglishCharsToRussianOnesUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.SaveVehicleRegInfoEnteringIsSkippedStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.CheckVehicleRegCertificateCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.GetVehicleRegCertificateNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.SaveVehicleRegCertificateNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleRegCertificateVM @Inject constructor(
    getCertificateNumberUC: GetVehicleRegCertificateNumberUseCase,
    private val saveCertificateNumberUC: SaveVehicleRegCertificateNumberUseCase,
    private val checkCertificateNumberCorrectnessUC: CheckVehicleRegCertificateCorrectnessUseCase,
    private val translateEnglishCharsToRussianOnesUC: TranslateEnglishCharsToRussianOnesUseCase,
    private val saveVehicleRegInfoEnteringIsSkippedStateUC: SaveVehicleRegInfoEnteringIsSkippedStateUseCase
): ViewModel() {
    // True only after ViewModel creation. After first get() it will be False till the ViewModel destruction
    private var _thisActivityCreatedFromScratch = true
    val thisActivityCreatedFromScratch: Boolean
        get() {
            return if(_thisActivityCreatedFromScratch){
                _thisActivityCreatedFromScratch = false
                true
            } else {
                false
            }
        }
    val lastSavedCorrectNumber = getCertificateNumberUC.execute().certificateNumberValue

    private var _enteredCertificateCorrectnessLiveData = MutableLiveData<Boolean>()
    init {_enteredCertificateCorrectnessLiveData.value = lastSavedCorrectNumber != "" }
    val enteredCertificateCorrectnessLiveData = _enteredCertificateCorrectnessLiveData as LiveData<Boolean>

    private var _certificateWasSuccessfullySaved = MutableLiveData<Boolean>()
    init{_certificateWasSuccessfullySaved.value = false }
    val certificateWasSuccessfullySaved = _certificateWasSuccessfullySaved as LiveData<Boolean>

    private var _vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved = MutableLiveData<Boolean>()
    init{_vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.value = false }
    val vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved = _vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved as LiveData<Boolean>

    private var _translatedToRussianString = MutableLiveData<String>()
    init{_translatedToRussianString.value = lastSavedCorrectNumber }
    val stringTranslatedToRussian = _translatedToRussianString as LiveData<String>

    fun checkCertificateCorrectness(certificate: String){
        val certificatePassedTheCheck = checkCertificateNumberCorrectnessUC.execute(
            regCertificate = com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToCheck(
                certificate
            )
        ).result
        _enteredCertificateCorrectnessLiveData.value = certificatePassedTheCheck

        // For case when returning to corresponding Activity with "back" and then changing EditText
        if((!certificatePassedTheCheck) and (_certificateWasSuccessfullySaved.value == true)){
            _certificateWasSuccessfullySaved.value = false
        }
    }

    fun saveCertificateNumber(certificate: String){
        _certificateWasSuccessfullySaved.value = saveCertificateNumberUC.execute(
            certificateNumberToSave = com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave(
                certificate
            )
        ).result
    }

    fun translateEnglishCharsToRussianOnes(strWithEnglishChar: String){
        _translatedToRussianString.value = translateEnglishCharsToRussianOnesUC.execute(
            strWithEnglishChars = com.example.testtaskforbipru.domainLayer.entities.utilities.StringWithEnglishCharsToTranslate(
                str = strWithEnglishChar
            )
        ).str
    }

    fun saveVehicleRegInfoEnteringIsSkippedState(state: Boolean){
        _vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.value = saveVehicleRegInfoEnteringIsSkippedStateUC.execute(
            com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateToSave(
                state
            )
        ).result
    }
}