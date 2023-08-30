package com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage

import android.content.Context
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.DriverLicenceInfoToSave
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.ReceivedDrivingLicenceInfo
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.SavingDrivingLicenceInfoResult

class SharedPrefsDriverLicenceInfoStorageImpl(context: Context):
    DriverLicenceInfoStorage {
    private val shPrefs = context.getSharedPreferences("driver_licence_info", Context.MODE_PRIVATE)
    private val shPrefsEditor = shPrefs.edit()

    override fun getDriverLicenceInfo(): ReceivedDrivingLicenceInfo {
        val licenceNumber = shPrefs.getString("driving_licence_number", "")
        return ReceivedDrivingLicenceInfo(
            licenceNumber
        )
    }

    override fun saveDriverLicenceInfo(param: DriverLicenceInfoToSave): SavingDrivingLicenceInfoResult {
        shPrefsEditor.putString("driving_licence_number", param.licenceNumber)
        return SavingDrivingLicenceInfoResult(
            shPrefsEditor.commit()
        )
    }
}