package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberSavingResult
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToSave
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class SaveDrivingLicenceNumberUseCaseTest {
    private val repo: DrivingLicenceNumberRepository = Mockito.mock()

    @AfterEach
    fun clearMocks(){
        Mockito.reset(repo)
    }

    @Test
    fun `returns True if save is successful`(){
        Mockito.`when`(repo.saveDrivingLicenceNumber(any())).thenReturn(DrivingLicenceNumberSavingResult(true))

        val useCase = SaveDrivingLicenceNumberUseCase(repo)
        val actual = useCase.execute(DrivingLicenceNumberToSave("")).result
        val expected = true
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `returns False if save is unsuccessful`(){
        Mockito.`when`(repo.saveDrivingLicenceNumber(any())).thenReturn(DrivingLicenceNumberSavingResult(false))

        val useCase = SaveDrivingLicenceNumberUseCase(repo)
        val actual = useCase.execute(DrivingLicenceNumberToSave("")).result
        val expected = false
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `doesn't run other repository methods`(){
        Mockito.`when`(repo.saveDrivingLicenceNumber(any())).thenReturn(DrivingLicenceNumberSavingResult(true))

        val useCase = SaveDrivingLicenceNumberUseCase(repo)
        useCase.execute(DrivingLicenceNumberToSave(""))

        Mockito.verify(repo, Mockito.never()).getDrivingLicenceNumber()
    }
}