package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.ReceivedRegistrationCertificateNumber
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave

interface VehicleRegCertificateNumberRepository {
    fun getVehicleRegistrationCertificateNumber(): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.ReceivedRegistrationCertificateNumber
    fun saveVehicleRegistrationCertificateNumber(param: com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult
}