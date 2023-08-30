package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.ReceivedRegistrationIdentifierNumber
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

class GetVehicleRegIdentifierNumberUseCaseTest {
    private val repo: VehicleRegIdentifierNumberRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns data from repository without changing it`(){
        Mockito.`when`(repo.getVehicleRegistrationIdentifierNumber()).thenReturn(
            ReceivedRegistrationIdentifierNumber("correct")
        )

        val useCase = GetVehicleRegIdentifierNumberUseCase(repo = repo)
        val actual = useCase.execute().identifierNumberValue
        val expected = "correct"
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.getVehicleRegistrationIdentifierNumber()).thenReturn(
            ReceivedRegistrationIdentifierNumber("")
        )

        val useCase = GetVehicleRegIdentifierNumberUseCase(repo = repo)
        useCase.execute()
        Mockito.verify(repo, Mockito.never()).saveVehicleRegistrationIdentifierNumber(any())
    }
}