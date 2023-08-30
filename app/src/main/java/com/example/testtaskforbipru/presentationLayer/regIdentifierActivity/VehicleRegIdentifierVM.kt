package com.example.testtaskforbipru.presentationLayer.regIdentifierActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskforbipru.domainLayer.usecases.utilities.TranslateEnglishCharsToRussianOnesUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.SaveVehicleRegInfoEnteringIsSkippedStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.CheckVehicleRegIdentifierCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.GetVehicleRegIdentifierNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.SaveVehicleRegIdentifierNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleRegIdentifierVM @Inject constructor(
    getIdentifierNumberUC: GetVehicleRegIdentifierNumberUseCase,
    private val saveIdentifierNumberUC: SaveVehicleRegIdentifierNumberUseCase,
    private val checkIdentifierNumberCorrectnessUC: CheckVehicleRegIdentifierCorrectnessUseCase,
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
    val lastSavedCorrectNumber = getIdentifierNumberUC.execute().identifierNumberValue

    private var _enteredIdentifierCorrectnessLiveData = MutableLiveData<Boolean>()
    init {_enteredIdentifierCorrectnessLiveData.value = lastSavedCorrectNumber != "" }
    val enteredIdentifierCorrectnessLiveData = _enteredIdentifierCorrectnessLiveData as LiveData<Boolean>

    private var _identifierWasSuccessfullySaved = MutableLiveData<Boolean>()
    init{_identifierWasSuccessfullySaved.value = false }
    val identifierWasSuccessfullySaved = _identifierWasSuccessfullySaved as LiveData<Boolean>

    private var _vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved = MutableLiveData<Boolean>()
    init{_vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.value = false }
    val vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved = _vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved as LiveData<Boolean>

    private var _translatedToRussianString = MutableLiveData<String>()
    init{_translatedToRussianString.value = lastSavedCorrectNumber }
    val stringTranslatedToRussian = _translatedToRussianString as LiveData<String>

    fun checkIdentifierCorrectness(identifier: String){
        val identifierPassedTheCheck = checkIdentifierNumberCorrectnessUC.execute(
            regIdentifier = com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToCheck(
                identifierNumber = identifier
            )
        ).result
        _enteredIdentifierCorrectnessLiveData.value = identifierPassedTheCheck

        // For case when returning to corresponding Activity with "back" and then changing EditText
        if((!identifierPassedTheCheck) and (_identifierWasSuccessfullySaved.value == true)){
            _identifierWasSuccessfullySaved.value = false
        }
    }

    fun saveIdentifierNumber(identifier: String){
        _identifierWasSuccessfullySaved.value = saveIdentifierNumberUC.execute(
            regIdentifierNumberToSave = com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToSave(
                identifier
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