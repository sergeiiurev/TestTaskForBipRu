package com.example.testtaskforbipru.di

import android.content.Context
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.DataEnteringInfoStorage
import com.example.testtaskforbipru.dataLayer.dataEnteringInfoStorage.SharedPrefsDataEnteringInfoStorageImpl
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.DriverLicenceInfoStorage
import com.example.testtaskforbipru.dataLayer.driverLicenceInfoStorage.SharedPrefsDriverLicenceInfoStorageImpl
import com.example.testtaskforbipru.dataLayer.repositories.DataEnteringInfoRepositoryImpl
import com.example.testtaskforbipru.dataLayer.repositories.DriverLicenceInfoRepositoryImpl
import com.example.testtaskforbipru.dataLayer.repositories.VehicleRegInfoRepositoryImpl
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.SharedPrefsVehicleRegInfoStorageImpl
import com.example.testtaskforbipru.dataLayer.vehicleRegistrationInfoStorage.VehicleRegistrationInfoStorage
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.DataEnteringEndingStateRepository
import com.example.testtaskforbipru.domainLayer.usecases.drivingLicence.DrivingLicenceNumberRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate.VehicleRegCertificateNumberRepository
import com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier.VehicleRegIdentifierNumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataLayerModule {
    @Provides
    @Singleton
    fun provideDriverLicenceInfoStorage(@ApplicationContext appContext: Context): DriverLicenceInfoStorage {
        return SharedPrefsDriverLicenceInfoStorageImpl(context = appContext)
    }

    @Provides
    @Singleton
    fun provideVehicleRegistrationInfoStorage(@ApplicationContext appContext: Context): VehicleRegistrationInfoStorage {
        return SharedPrefsVehicleRegInfoStorageImpl(context = appContext)
    }

    @Provides
    @Singleton
    fun provideDataEnteringInfoStorage(@ApplicationContext appContext: Context): DataEnteringInfoStorage {
        return SharedPrefsDataEnteringInfoStorageImpl(context = appContext)
    }

    @Provides
    @Singleton
    fun provideDrivingLicenceRepository(storage: DriverLicenceInfoStorage): DrivingLicenceNumberRepository {
        return DriverLicenceInfoRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideDataEnteringEndingStateRepository(storage: DataEnteringInfoStorage): DataEnteringEndingStateRepository{
        return DataEnteringInfoRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideVehicleRegInfoEnteringIsSkippedStateRepository(storage: DataEnteringInfoStorage): VehicleRegInfoEnteringIsSkippedStateRepository {
        return DataEnteringInfoRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideVehicleRegCertificateNumberRepository(storage: VehicleRegistrationInfoStorage): VehicleRegCertificateNumberRepository{
        return VehicleRegInfoRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideVehicleRegIdentifierNumberRepository(storage: VehicleRegistrationInfoStorage): VehicleRegIdentifierNumberRepository{
        return VehicleRegInfoRepositoryImpl(storage)
    }
}