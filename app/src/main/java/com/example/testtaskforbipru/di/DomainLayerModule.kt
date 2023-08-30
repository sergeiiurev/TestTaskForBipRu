package com.example.testtaskforbipru.di

import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.DataEnteringEndingStateRepository
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.GetDataEnteringEndingStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.SaveDataEnteringEndingStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.CheckDrivingLicenceNumberCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.DrivingLicenceNumberRepository
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.GetDrivingLicenceNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.SaveDrivingLicenceNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.utilities.TranslateEnglishCharsToRussianOnesUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.GetVehicleRegInfoEnteringIsSkippedStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.SaveVehicleRegInfoEnteringIsSkippedStateUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.CheckVehicleRegCertificateCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.GetVehicleRegCertificateNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.SaveVehicleRegCertificateNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.VehicleRegCertificateNumberRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.CheckVehicleRegIdentifierCorrectnessUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.GetVehicleRegIdentifierNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.SaveVehicleRegIdentifierNumberUseCase
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.VehicleRegIdentifierNumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainLayerModule {
    @Provides
    fun provideGetDataEnteringEndingStateUseCase(repo: DataEnteringEndingStateRepository): GetDataEnteringEndingStateUseCase {
        return GetDataEnteringEndingStateUseCase(repo)
    }

    @Provides
    fun provideSaveDataEnteringEndingStateUseCase(repo: DataEnteringEndingStateRepository): SaveDataEnteringEndingStateUseCase {
        return SaveDataEnteringEndingStateUseCase(repo)
    }

    @Provides
    fun provideGetDrivingLicenceNumberUseCase(repo: DrivingLicenceNumberRepository): GetDrivingLicenceNumberUseCase {
        return GetDrivingLicenceNumberUseCase(repo)
    }

    @Provides
    fun provideSaveDrivingLicenceNumberUseCase(repo: DrivingLicenceNumberRepository): SaveDrivingLicenceNumberUseCase {
        return SaveDrivingLicenceNumberUseCase(repo)
    }

    @Provides
    fun provideCheckDrivingLicenceNumberCorrectnessUseCase(): CheckDrivingLicenceNumberCorrectnessUseCase {
        return CheckDrivingLicenceNumberCorrectnessUseCase()
    }

    @Provides
    fun provideTranslateEnglishCharsToRussianOnesUseCase(): TranslateEnglishCharsToRussianOnesUseCase {
        return TranslateEnglishCharsToRussianOnesUseCase()
    }

    @Provides
    fun provideGetVehicleRegInfoEnteringIsSkippedStateUseCase(repo: VehicleRegInfoEnteringIsSkippedStateRepository): GetVehicleRegInfoEnteringIsSkippedStateUseCase {
        return GetVehicleRegInfoEnteringIsSkippedStateUseCase(repo)
    }

    @Provides
    fun provideSaveVehicleRegInfoEnteringIsSkippedStateUseCase(repo: VehicleRegInfoEnteringIsSkippedStateRepository): SaveVehicleRegInfoEnteringIsSkippedStateUseCase {
        return SaveVehicleRegInfoEnteringIsSkippedStateUseCase(repo)
    }

    @Provides
    fun provideCheckVehicleRegCertificateCorrectnessUseCase(): CheckVehicleRegCertificateCorrectnessUseCase {
        return CheckVehicleRegCertificateCorrectnessUseCase()
    }

    @Provides
    fun provideGetVehicleRegCertificateNumberUseCase(repo: VehicleRegCertificateNumberRepository): GetVehicleRegCertificateNumberUseCase {
        return GetVehicleRegCertificateNumberUseCase(repo)
    }

    @Provides
    fun provideSaveVehicleRegCertificateNumberUseCase(repo: VehicleRegCertificateNumberRepository): SaveVehicleRegCertificateNumberUseCase {
        return SaveVehicleRegCertificateNumberUseCase(repo)
    }

    @Provides
    fun provideCheckVehicleRegIdentifierCorrectnessUseCase(): CheckVehicleRegIdentifierCorrectnessUseCase {
        return CheckVehicleRegIdentifierCorrectnessUseCase()
    }

    @Provides
    fun provideGetVehicleRegIdentifierNumberUseCase(repo: VehicleRegIdentifierNumberRepository): GetVehicleRegIdentifierNumberUseCase {
        return GetVehicleRegIdentifierNumberUseCase(repo)
    }

    @Provides
    fun provideSaveVehicleRegIdentifierNumberUseCase(repo: VehicleRegIdentifierNumberRepository): SaveVehicleRegIdentifierNumberUseCase {
        return SaveVehicleRegIdentifierNumberUseCase(repo)
    }
}