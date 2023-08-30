package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegInfoEnteringIsSkippedState

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.ReceivedVehicleRegInfoEnteringIsSkippedState
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegInfoEnteringIsSkippedState.VehicleRegInfoEnteringIsSkippedStateToSave
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class SaveVehicleRegInfoEnteringIsSkippedStateUseCaseTest {
    private val repo: VehicleRegInfoEnteringIsSkippedStateRepository = Mockito.mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns True if save is successful`(){
        Mockito.`when`(repo.saveVehicleRegInfoIsSkippedState(any())).thenReturn(VehicleRegInfoEnteringIsSkippedStateSavingResult(result = true))

        val useCase = SaveVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        val actual = useCase.execute(VehicleRegInfoEnteringIsSkippedStateToSave(state = false)).result
        val expected = true
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False if save is unsuccessful`(){
        Mockito.`when`(repo.saveVehicleRegInfoIsSkippedState(any())).thenReturn(VehicleRegInfoEnteringIsSkippedStateSavingResult(result = false))

        val useCase = SaveVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        val actual = useCase.execute(VehicleRegInfoEnteringIsSkippedStateToSave(state = false)).result
        val expected = false
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.saveVehicleRegInfoIsSkippedState(any())).thenReturn(VehicleRegInfoEnteringIsSkippedStateSavingResult(true))

        val useCase = SaveVehicleRegInfoEnteringIsSkippedStateUseCase(repo = repo)
        useCase.execute(VehicleRegInfoEnteringIsSkippedStateToSave(false))

        Mockito.verify(repo, Mockito.never()).getVehicleRegInfoEnteringIsSkippedState()
    }
}