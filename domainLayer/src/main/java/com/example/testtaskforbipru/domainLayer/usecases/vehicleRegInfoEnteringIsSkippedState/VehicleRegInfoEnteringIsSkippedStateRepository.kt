package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateToSave
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState

interface VehicleRegInfoEnteringIsSkippedStateRepository {
    fun getVehicleRegInfoEnteringIsSkippedState(): com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState
    fun saveVehicleRegInfoIsSkippedState(state: com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateToSave): com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateSavingResult
}