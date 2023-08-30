package com.example.testtaskforbipru.dataLayer.repositories

import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.DriverLicenceInfoStorage
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.DriverLicenceInfoToSave
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.entities.ReceivedDrivingLicenceInfo
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.ReceivedDrivingLicenceNumber
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.DrivingLicenceNumberRepository

class DriverLicenceInfoRepositoryImpl(private val storage: DriverLicenceInfoStorage): DrivingLicenceNumberRepository {
    override fun getDrivingLicenceNumber(): ReceivedDrivingLicenceNumber {
        return mapToDomain(storage.getDriverLicenceInfo())
    }

    override fun saveDrivingLicenceNumber(param: DrivingLicenceNumberToSave): DrivingLicenceNumberSavingResult {
        return DrivingLicenceNumberSavingResult(result = storage.saveDriverLicenceInfo(mapToData(param)).result)
    }

    //region Mappers
    private fun mapToDomain(param: ReceivedDrivingLicenceInfo): ReceivedDrivingLicenceNumber{
        return ReceivedDrivingLicenceNumber(param.licenceNumber)
    }

    private fun mapToData(param: DrivingLicenceNumberToSave): DriverLicenceInfoToSave {
        return DriverLicenceInfoToSave(param.licenceNumberValue)
    }
    //endregion
}