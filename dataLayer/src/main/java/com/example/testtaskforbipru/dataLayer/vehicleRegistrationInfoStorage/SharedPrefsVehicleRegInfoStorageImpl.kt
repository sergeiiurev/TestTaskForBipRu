package com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage

import android.content.Context
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.ReceivedRegistrationInfo
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.RegCertificateToSave
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.RegIdentifierToSave
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.SavingRegCertificateInfoResult
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.SavingRegIdentifierInfoResult

class SharedPrefsVehicleRegInfoStorageImpl(context: Context):
    VehicleRegistrationInfoStorage {
    private val shPrefs = context.getSharedPreferences("vehicle_reg_info", Context.MODE_PRIVATE)
    private val shPrefsEditor = shPrefs.edit()

    override fun getRegistrationInfo(): ReceivedRegistrationInfo {
        val regCertificate = shPrefs.getString("registration_certificate", "")
        val regIdentifier = shPrefs.getString("registration_identifier", "")
        return ReceivedRegistrationInfo(
            identifier = regIdentifier,
            certificate = regCertificate
        )
    }

    override fun saveRegistrationInfo(param: RegCertificateToSave): SavingRegCertificateInfoResult {
        shPrefsEditor.putString("registration_certificate", param.certificateNumber)
        return SavingRegCertificateInfoResult(
            shPrefsEditor.commit()
        )
    }

    override fun saveRegistrationInfo(param: RegIdentifierToSave): SavingRegIdentifierInfoResult {
        shPrefsEditor.putString("registration_identifier", param.identifierNumber)
        return SavingRegIdentifierInfoResult(
            shPrefsEditor.commit()
        )
    }
}