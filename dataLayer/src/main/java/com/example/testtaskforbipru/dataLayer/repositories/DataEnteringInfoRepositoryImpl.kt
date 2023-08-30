package com.example.testtaskforbipru.dataLayer.repositories

import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.DataEnteringInfoStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.DataEnteringEndingStateToSaveInStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.VehicleRegInfoEnteringIsSkippedStateToSaveInStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.ReceivedFromStorageDataEnteringInfo
import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateToSave
import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.ReceivedDataEnteringEndingState
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateToSave
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.DataEnteringEndingStateRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateRepository

class DataEnteringInfoRepositoryImpl(private val storage: DataEnteringInfoStorage): DataEnteringEndingStateRepository, VehicleRegInfoEnteringIsSkippedStateRepository {
    override fun getDataEnteringEndingState(): ReceivedDataEnteringEndingState {
        return mapEndingStateToDomain(storage.getDataEnteringInfo())
    }

    override fun saveDataEnteringEndingState(state: DataEnteringEndingStateToSave): DataEnteringEndingStateSavingResult {
        return DataEnteringEndingStateSavingResult(result = storage.saveDataEnteringInfo(mapToData(state)).result)
    }

    override fun getVehicleRegInfoEnteringIsSkippedState(): ReceivedVehicleRegInfoEnteringIsSkippedState {
        return mapNoSkipsStateToDomain(storage.getDataEnteringInfo())
    }

    override fun saveVehicleRegInfoIsSkippedState(state: VehicleRegInfoEnteringIsSkippedStateToSave): VehicleRegInfoEnteringIsSkippedStateSavingResult {
        return VehicleRegInfoEnteringIsSkippedStateSavingResult(storage.saveDataEnteringInfo(mapToData(state)).result)
    }

    //region Mappers
    private fun mapToData(param: DataEnteringEndingStateToSave): DataEnteringEndingStateToSaveInStorage {
        return DataEnteringEndingStateToSaveInStorage(
            param.state
        )
    }

    private fun mapEndingStateToDomain(param: ReceivedFromStorageDataEnteringInfo): ReceivedDataEnteringEndingState{
        return ReceivedDataEnteringEndingState(param.endingState)
    }

    private fun mapToData(param: VehicleRegInfoEnteringIsSkippedStateToSave): VehicleRegInfoEnteringIsSkippedStateToSaveInStorage {
        return VehicleRegInfoEnteringIsSkippedStateToSaveInStorage(
            param.state
        )
    }

    private fun mapNoSkipsStateToDomain(param: ReceivedFromStorageDataEnteringInfo): ReceivedVehicleRegInfoEnteringIsSkippedState{
        return ReceivedVehicleRegInfoEnteringIsSkippedState(param.noSkipsState)
    }
    //endregion
}