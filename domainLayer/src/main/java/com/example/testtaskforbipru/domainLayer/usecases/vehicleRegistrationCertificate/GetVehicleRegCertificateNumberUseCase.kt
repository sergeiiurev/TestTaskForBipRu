package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.ReceivedRegistrationCertificateNumber

class GetVehicleRegCertificateNumberUseCase(private val repo: VehicleRegCertificateNumberRepository) {
    fun execute(): ReceivedRegistrationCertificateNumber {
        return repo.getVehicleRegistrationCertificateNumber()
    }
}