package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToSave
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class SaveVehicleRegIdentifierNumberUseCaseTest {
    private val repo: VehicleRegIdentifierNumberRepository = Mockito.mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns True if save is successful`(){
        Mockito.`when`(repo.saveVehicleRegistrationIdentifierNumber(any())).thenReturn(
            RegIdentifierNumberSavingResult(result = true)
        )

        val useCase = SaveVehicleRegIdentifierNumberUseCase(repo = repo)
        val actual = useCase.execute(RegIdentifierNumberToSave(identifierNumberValue = "")).result
        val expected = true
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False if save is unsuccessful`(){
        Mockito.`when`(repo.saveVehicleRegistrationIdentifierNumber(any())).thenReturn(
            RegIdentifierNumberSavingResult(result = false)
        )

        val useCase = SaveVehicleRegIdentifierNumberUseCase(repo = repo)
        val actual = useCase.execute(RegIdentifierNumberToSave(identifierNumberValue = "")).result
        val expected = false
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.saveVehicleRegistrationIdentifierNumber(any())).thenReturn(
            RegIdentifierNumberSavingResult(result = true)
        )

        val useCase = SaveVehicleRegIdentifierNumberUseCase(repo = repo)
        useCase.execute(RegIdentifierNumberToSave(identifierNumberValue = ""))

        Mockito.verify(repo, Mockito.never()).getVehicleRegistrationIdentifierNumber()
    }
}