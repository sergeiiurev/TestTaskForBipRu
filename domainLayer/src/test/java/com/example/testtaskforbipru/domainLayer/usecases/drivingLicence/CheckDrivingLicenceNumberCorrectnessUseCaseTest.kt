package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToCheck
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CheckDrivingLicenceNumberCorrectnessUseCaseTest {
    private val useCase = CheckDrivingLicenceNumberCorrectnessUseCase()

    private fun makeTest(testNumber: String, expectedResult: Boolean){
        val correctTestLicenceNumber = DrivingLicenceNumberToCheck(drivingLicenceNumber = testNumber)

        val actual = useCase.execute(correctTestLicenceNumber).result
        Assertions.assertEquals(expectedResult, actual)
    }

    @Test
    fun `correct licence number (type with russian chars) passes the check successfully`(){
        makeTest(testNumber = "12ЧБ123456", expectedResult = true)
    }

    @Test
    fun `correct licence number (type with digits only) passes the check successfully`(){
        makeTest(testNumber = "1234567890", expectedResult = true)
    }

    @Test
    fun `correct licence number with spaces passes the check successfully`(){
        makeTest(testNumber = "  12  34  567890  ", expectedResult = true)
    }

    @Test
    fun `licence number with line break chars doesn't pass the check`(){
        makeTest(testNumber = "12\nЧБ\n123456", expectedResult = false)
    }

    @Test
    fun `licence number with english chars doesn't pass the check`(){
        makeTest(testNumber = "12 YY 123456", expectedResult = false)
    }

    @Test
    fun `licence number with unsuitable russian chars doesn't pass the check`(){
        makeTest(testNumber = "12 ЗИ 123456", expectedResult = false)
    }

    @Test
    fun `too long licence number doesn't pass the check`(){
        makeTest(testNumber = "12                                    34                             567890", expectedResult = false)
    }

    @Test
    fun `licence number with fully separated symbols doesn't pass the check`(){
        makeTest(testNumber = "1 2 3 4 5 6 7 8 9 0", expectedResult = false)
    }
}