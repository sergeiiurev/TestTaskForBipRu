package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave

class SaveVehicleRegCertificateNumberUseCase(private val repo: VehicleRegCertificateNumberRepository) {
    fun execute(certificateNumberToSave: com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult {
        return repo.saveVehicleRegistrationCertificateNumber(certificateNumberToSave)
    }
}