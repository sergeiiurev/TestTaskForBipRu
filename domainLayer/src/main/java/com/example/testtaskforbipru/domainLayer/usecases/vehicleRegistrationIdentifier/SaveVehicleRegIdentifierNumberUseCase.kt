package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToSave

class SaveVehicleRegIdentifierNumberUseCase(private val repo: VehicleRegIdentifierNumberRepository) {
    fun execute(regIdentifierNumberToSave: com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToSave): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberSavingResult {
        return repo.saveVehicleRegistrationIdentifierNumber(regIdentifierNumberToSave)
    }
}