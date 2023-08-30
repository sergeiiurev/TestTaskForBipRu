package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToSave
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveVehicleRegCertificateNumberUseCaseTest {
    private val repo: VehicleRegCertificateNumberRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns True if save is successful`(){
        Mockito.`when`(repo.saveVehicleRegistrationCertificateNumber(any())).thenReturn(
            RegCertificateNumberSavingResult(result = true)
        )

        val useCase = SaveVehicleRegCertificateNumberUseCase(repo = repo)
        val actual = useCase.execute(RegCertificateNumberToSave(certificateNumberValue = "")).result
        val expected = true
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False if save is unsuccessful`(){
        Mockito.`when`(repo.saveVehicleRegistrationCertificateNumber(any())).thenReturn(
            RegCertificateNumberSavingResult(result = false)
        )

        val useCase = SaveVehicleRegCertificateNumberUseCase(repo = repo)
        val actual = useCase.execute(RegCertificateNumberToSave(certificateNumberValue = "")).result
        val expected = false
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.saveVehicleRegistrationCertificateNumber(any())).thenReturn(
            RegCertificateNumberSavingResult(result = true)
        )

        val useCase = SaveVehicleRegCertificateNumberUseCase(repo = repo)
        useCase.execute(RegCertificateNumberToSave(certificateNumberValue = ""))

        Mockito.verify(repo, Mockito.never()).getVehicleRegistrationCertificateNumber()
    }
}