package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.ReceivedRegistrationCertificateNumber
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class GetVehicleRegCertificateNumberUseCaseTest {
    private val repo: VehicleRegCertificateNumberRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns data from repository without changing it`(){
        Mockito.`when`(repo.getVehicleRegistrationCertificateNumber()).thenReturn(
            ReceivedRegistrationCertificateNumber("correct")
        )

        val useCase = GetVehicleRegCertificateNumberUseCase(repo = repo)
        val actual = useCase.execute().certificateNumberValue
        val expected = "correct"
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.getVehicleRegistrationCertificateNumber()).thenReturn(
            ReceivedRegistrationCertificateNumber("")
        )

        val useCase = GetVehicleRegCertificateNumberUseCase(repo = repo)
        useCase.execute()
        Mockito.verify(repo, Mockito.never()).saveVehicleRegistrationCertificateNumber(any())
    }
}