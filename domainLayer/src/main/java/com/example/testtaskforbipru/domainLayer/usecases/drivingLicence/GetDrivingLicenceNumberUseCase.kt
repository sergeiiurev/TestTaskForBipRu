package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.ReceivedDrivingLicenceNumber

class GetDrivingLicenceNumberUseCase(private val repo: DrivingLicenceNumberRepository) {
    fun execute(): com.example.testtaskforbipru.domainLayer.entities.drivingLicence.ReceivedDrivingLicenceNumber {
        return repo.getDrivingLicenceNumber()
    }
}