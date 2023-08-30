package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

class GetVehicleRegInfoEnteringIsSkippedStateUseCaseTest {
    private val repo: VehicleRegInfoEnteringIsSkippedStateRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns True from repository without changing it`(){
        val testReceivedVehicleRegInfoEnteringIsSkippedState = ReceivedVehicleRegInfoEnteringIsSkippedState(true)
        Mockito.`when`(repo.getVehicleRegInfoEnteringIsSkippedState()).thenReturn(testReceivedVehicleRegInfoEnteringIsSkippedState)

        val useCase = GetVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        val actual = useCase.execute().state
        val expected = true
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False from repository without changing it`(){
        val testReceivedVehicleRegInfoEnteringIsSkippedState = ReceivedVehicleRegInfoEnteringIsSkippedState(false)
        Mockito.`when`(repo.getVehicleRegInfoEnteringIsSkippedState()).thenReturn(testReceivedVehicleRegInfoEnteringIsSkippedState)

        val useCase = GetVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        val actual = useCase.execute().state
        val expected = false
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        val testReceivedVehicleRegInfoEnteringIsSkippedState = ReceivedVehicleRegInfoEnteringIsSkippedState(false)
        Mockito.`when`(repo.getVehicleRegInfoEnteringIsSkippedState()).thenReturn(testReceivedVehicleRegInfoEnteringIsSkippedState)

        val useCase = GetVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        useCase.execute()
        Mockito.verify(repo, Mockito.never()).saveVehicleRegInfoIsSkippedState(any())
    }
}