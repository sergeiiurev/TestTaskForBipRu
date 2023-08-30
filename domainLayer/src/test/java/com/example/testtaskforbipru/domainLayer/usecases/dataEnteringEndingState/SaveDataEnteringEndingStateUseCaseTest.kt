package com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState

import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateToSave
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class SaveDataEnteringEndingStateUseCaseTest {
    private val dataEnteringEndingStateRepo: DataEnteringEndingStateRepository = Mockito.mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(dataEnteringEndingStateRepo)
    }

    @Test
    fun `returns True if save is successful`(){
        Mockito.`when`(dataEnteringEndingStateRepo.saveDataEnteringEndingState(any()))
            .thenReturn(DataEnteringEndingStateSavingResult(true))

        val testDataEnteringEndingStateToSave = DataEnteringEndingStateToSave(state = any())
        val useCase = SaveDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)

        val actual = useCase.execute(testDataEnteringEndingStateToSave).result
        val expected = true

        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `returns False if save is unsuccessful`(){
        Mockito.`when`(dataEnteringEndingStateRepo.saveDataEnteringEndingState(any()))
            .thenReturn(DataEnteringEndingStateSavingResult(false))

        val testDataEnteringEndingStateToSave = DataEnteringEndingStateToSave(state = any())
        val useCase = SaveDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)

        val actual = useCase.execute(testDataEnteringEndingStateToSave).result
        val expected = false

        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(dataEnteringEndingStateRepo.saveDataEnteringEndingState(any()))
            .thenReturn(DataEnteringEndingStateSavingResult(false))

        val testDataEnteringEndingStateToSave = DataEnteringEndingStateToSave(state = any())
        val useCase = SaveDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)

        useCase.execute(testDataEnteringEndingStateToSave)

        Mockito.verify(dataEnteringEndingStateRepo, Mockito.never()).getDataEnteringEndingState()
    }
}