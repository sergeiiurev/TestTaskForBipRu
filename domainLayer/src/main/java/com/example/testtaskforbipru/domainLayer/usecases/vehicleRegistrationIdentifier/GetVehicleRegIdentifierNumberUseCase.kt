package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.ReceivedRegistrationIdentifierNumber

class GetVehicleRegIdentifierNumberUseCase(private val repo: VehicleRegIdentifierNumberRepository) {
    fun execute(): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.ReceivedRegistrationIdentifierNumber {
        return repo.getVehicleRegistrationIdentifierNumber()
    }
}