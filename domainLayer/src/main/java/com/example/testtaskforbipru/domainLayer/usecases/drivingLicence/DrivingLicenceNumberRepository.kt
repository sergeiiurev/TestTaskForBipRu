package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.ReceivedDrivingLicenceNumber

interface DrivingLicenceNumberRepository {
    fun getDrivingLicenceNumber(): ReceivedDrivingLicenceNumber
    fun saveDrivingLicenceNumber(param: DrivingLicenceNumberToSave): DrivingLicenceNumberSavingResult
}