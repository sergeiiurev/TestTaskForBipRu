package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.ReceivedDrivingLicenceNumber
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

class GetDrivingLicenceNumberUseCaseTest {
    private val repo: DrivingLicenceNumberRepository = mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns data from repository without changing it`(){
        Mockito.`when`(repo.getDrivingLicenceNumber()).thenReturn(ReceivedDrivingLicenceNumber("correct"))

        val useCase = GetDrivingLicenceNumberUseCase(repo)
        val actual = useCase.execute().licenceNumberValue
        val expected = "correct"
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.getDrivingLicenceNumber()).thenReturn(ReceivedDrivingLicenceNumber(""))
        val useCase = GetDrivingLicenceNumberUseCase(repo)
        useCase.execute().licenceNumberValue

        Mockito.verify(repo, Mockito.never()).saveDrivingLicenceNumber(any())
    }
}