package com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage

import android.content.Context
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.DataEnteringEndingStateToSaveInStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.VehicleRegInfoEnteringIsSkippedStateToSaveInStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.ReceivedFromStorageDataEnteringInfo
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.SavingDataEnteringEndingStateResult
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.entities.SavingVehicleRegInfoEnteringIsSkippedStateResult

class SharedPrefsDataEnteringInfoStorageImpl(context: Context):
    DataEnteringInfoStorage {
    private val shPrefs = context.getSharedPreferences("data_entering_info", Context.MODE_PRIVATE)
    private val shPrefsEditor = shPrefs.edit()

    override fun getDataEnteringInfo(): ReceivedFromStorageDataEnteringInfo {
        val endingState = shPrefs.getBoolean("data_entering_ending_state", false)
        val noSkipsState = shPrefs.getBoolean("vehicle_reg_info_entering_is_skipped_state", false)
        return ReceivedFromStorageDataEnteringInfo(
            endingState,
            noSkipsState
        )
    }

    override fun saveDataEnteringInfo(param: DataEnteringEndingStateToSaveInStorage): SavingDataEnteringEndingStateResult {
        shPrefsEditor.putBoolean("data_entering_ending_state", param.state)
        return SavingDataEnteringEndingStateResult(
            shPrefsEditor.commit()
        )
    }

    override fun saveDataEnteringInfo(param: VehicleRegInfoEnteringIsSkippedStateToSaveInStorage): SavingVehicleRegInfoEnteringIsSkippedStateResult {
        //VehicleRegInfoEnteringIsSkippedState
        shPrefsEditor.putBoolean("vehicle_reg_info_entering_is_skipped_state", param.state)
        return SavingVehicleRegInfoEnteringIsSkippedStateResult(
            shPrefsEditor.commit()
        )
    }
}