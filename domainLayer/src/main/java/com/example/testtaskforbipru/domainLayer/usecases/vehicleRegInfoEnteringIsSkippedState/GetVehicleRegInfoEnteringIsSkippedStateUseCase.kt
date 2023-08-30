package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState

class GetVehicleRegInfoEnteringIsSkippedStateUseCase(private val repo: VehicleRegInfoEnteringIsSkippedStateRepository) {
    fun execute(): com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState {
        return repo.getVehicleRegInfoEnteringIsSkippedState()
    }
}