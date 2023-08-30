package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave

class SaveDrivingLicenceNumberUseCase(private val repo: DrivingLicenceNumberRepository) {
    fun execute(licenceNumberToSave: com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave): com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberSavingResult {
        return repo.saveDrivingLicenceNumber(licenceNumberToSave)
    }
}