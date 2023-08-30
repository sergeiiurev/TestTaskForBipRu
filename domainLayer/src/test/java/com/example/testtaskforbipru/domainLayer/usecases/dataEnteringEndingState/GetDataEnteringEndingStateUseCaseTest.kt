package com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState

import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.ReceivedDataEnteringEndingState
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

class GetDataEnteringEndingStateUseCaseTest {
    private val dataEnteringEndingStateRepo: DataEnteringEndingStateRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(dataEnteringEndingStateRepo)
    }

    @Test
    fun `doesn't run other repository methods`(){
        var testReceivedState = ReceivedDataEnteringEndingState(true)
        Mockito.`when`(dataEnteringEndingStateRepo.getDataEnteringEndingState()).thenReturn(testReceivedState)

        val useCase = GetDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)
        useCase.execute()

        testReceivedState = ReceivedDataEnteringEndingState(false)
        useCase.execute()

        Mockito.verify(dataEnteringEndingStateRepo, Mockito.never()).saveDataEnteringEndingState(any())
    }

    @Test
    fun `returns True state from repository without changes`(){
        val trueState = ReceivedDataEnteringEndingState(true)
        Mockito.`when`(dataEnteringEndingStateRepo.getDataEnteringEndingState()).thenReturn(trueState)

        val useCase = GetDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)

        val actual = useCase.execute().state
        val expected = true

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False state from repository without changes`(){
        val falseState = ReceivedDataEnteringEndingState(false)
        Mockito.`when`(dataEnteringEndingStateRepo.getDataEnteringEndingState()).thenReturn(falseState)

        val useCase = GetDataEnteringEndingStateUseCase(dataEnteringEndingStateRepo)

        val actual = useCase.execute().state
        val expected = false

        Assertions.assertEquals(expected, actual)
    }


}