package com.example.testtaskforbipru.presentationLayer.drivingLicenceActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.CheckDrivingLicenceNumberCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.GetDrivingLicenceNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.SaveDrivingLicenceNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.utilities.TranslateEnglishCharsToRussianOnesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrivingLicenceVM @Inject constructor(
    getDrivingLicenceNumberUC: GetDrivingLicenceNumberUseCase,
    private val saveDrivingLicenceNumberUC: SaveDrivingLicenceNumberUseCase,
    private val checkDrivingLicenceNumberCorrectnessUC: CheckDrivingLicenceNumberCorrectnessUseCase,
    private val translateEnglishCharsToRussianOnesUC: TranslateEnglishCharsToRussianOnesUseCase
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
    val lastSavedCorrectNumber = getDrivingLicenceNumberUC.execute().licenceNumberValue

    private var _enteredDrivingLicenceCorrectnessLiveData = MutableLiveData<Boolean>()
    init {_enteredDrivingLicenceCorrectnessLiveData.value = lastSavedCorrectNumber != "" }
    val enteredDrivingLicenceCorrectnessLiveData = _enteredDrivingLicenceCorrectnessLiveData as LiveData<Boolean>

    private var _drivingLicenceWasSuccessfullySaved = MutableLiveData<Boolean>()
    init{_drivingLicenceWasSuccessfullySaved.value = false }
    val drivingLicenceWasSuccessfullySaved = _drivingLicenceWasSuccessfullySaved as LiveData<Boolean>

    private var _translatedToRussianString = MutableLiveData<String>()
    init{_translatedToRussianString.value = lastSavedCorrectNumber }
    val stringTranslatedToRussian = _translatedToRussianString as LiveData<String>

    fun checkDrivingLicenceCorrectness(drivingLicence: String){
        val drivingLicencePassedTheCheck = checkDrivingLicenceNumberCorrectnessUC.execute(
            drivingLicenceNumber = com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToCheck(
                drivingLicence
            )
        ).result
        _enteredDrivingLicenceCorrectnessLiveData.value = drivingLicencePassedTheCheck

        // For case when returning to corresponding Activity with "back" and then changing EditText
        if((!drivingLicencePassedTheCheck) and (_drivingLicenceWasSuccessfullySaved.value == true)){
            _drivingLicenceWasSuccessfullySaved.value = false
        }
    }

    fun saveDrivingLicenceNumber(drivingLicence: String){
        _drivingLicenceWasSuccessfullySaved.value = saveDrivingLicenceNumberUC.execute(
            licenceNumberToSave = com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave(
                licenceNumberValue = drivingLicence
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
}