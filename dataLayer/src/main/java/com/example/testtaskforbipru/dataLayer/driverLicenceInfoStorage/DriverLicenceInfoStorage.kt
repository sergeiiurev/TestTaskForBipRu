package com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage

import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.DriverLicenceInfoToSave
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.ReceivedDrivingLicenceInfo
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.SavingDrivingLicenceInfoResult

interface DriverLicenceInfoStorage {
    fun getDriverLicenceInfo(): ReceivedDrivingLicenceInfo
    fun saveDriverLicenceInfo(param: DriverLicenceInfoToSave): SavingDrivingLicenceInfoResult
}