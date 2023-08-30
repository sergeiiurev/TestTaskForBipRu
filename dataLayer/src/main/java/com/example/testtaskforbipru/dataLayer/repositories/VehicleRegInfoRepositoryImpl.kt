package com.example.testtaskforbipru.dataLayer.repositories

import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.VehicleRegistrationInfoStorage
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.ReceivedRegistrationInfo
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.RegCertificateToSave
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.entities.RegIdentifierToSave
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.ReceivedRegistrationCertificateNumber
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.ReceivedRegistrationIdentifierNumber
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToSave
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.VehicleRegCertificateNumberRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.VehicleRegIdentifierNumberRepository

class VehicleRegInfoRepositoryImpl(private val storage: VehicleRegistrationInfoStorage):
    VehicleRegCertificateNumberRepository,
    VehicleRegIdentifierNumberRepository
{
    override fun getVehicleRegistrationCertificateNumber(): ReceivedRegistrationCertificateNumber {
        return mapToDomainRegistrationCertificateNumber(storage.getRegistrationInfo())
    }

    override fun saveVehicleRegistrationCertificateNumber(param: RegCertificateNumberToSave): RegCertificateNumberSavingResult {
        return RegCertificateNumberSavingResult(result = storage.saveRegistrationInfo(mapToData(param)).result)
    }

    override fun getVehicleRegistrationIdentifierNumber(): ReceivedRegistrationIdentifierNumber {
        return mapToDomainRegistrationIdentifierNumber(storage.getRegistrationInfo())
    }

    override fun saveVehicleRegistrationIdentifierNumber(param: RegIdentifierNumberToSave): RegIdentifierNumberSavingResult {
        return RegIdentifierNumberSavingResult(result = storage.saveRegistrationInfo(mapToData(param)).result)
    }

    //region Mappers
    private fun mapToDomainRegistrationIdentifierNumber(param: ReceivedRegistrationInfo): ReceivedRegistrationIdentifierNumber{
        return ReceivedRegistrationIdentifierNumber(param.identifier ?: "")
    }

    private fun mapToDomainRegistrationCertificateNumber(param: ReceivedRegistrationInfo): ReceivedRegistrationCertificateNumber{
        return ReceivedRegistrationCertificateNumber(param.certificate ?: "")
    }

    private fun mapToData(param: RegCertificateNumberToSave): RegCertificateToSave {
        return RegCertificateToSave(
            param.certificateNumberValue
        )
    }

    private fun mapToData(param: RegIdentifierNumberToSave): RegIdentifierToSave {
        return RegIdentifierToSave(
            param.identifierNumberValue
        )
    }
    //endregion
}