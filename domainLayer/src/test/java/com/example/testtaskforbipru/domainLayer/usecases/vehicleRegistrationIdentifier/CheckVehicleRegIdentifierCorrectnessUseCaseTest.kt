package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToCheck
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CheckVehicleRegIdentifierCorrectnessUseCaseTest {
    private val useCase = CheckVehicleRegIdentifierCorrectnessUseCase()

    private fun makeTest(testNumber: String, expectedResult: Boolean){
        val regIdentifierNumberToCheck =  RegIdentifierNumberToCheck(identifierNumber = testNumber)

        val actual = useCase.execute(regIdentifierNumberToCheck).result
        Assertions.assertEquals(expectedResult, actual)
    }

    @Test
    fun `correct registration identifier (type 1A) passes the check successfully`(){
        makeTest(testNumber = "А 123 АА 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 1B) passes the check successfully`(){
        makeTest(testNumber = "АА 123 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 2) passes the check successfully`(){
        makeTest(testNumber = "АА 1234 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 3) passes the check successfully`(){
        makeTest(testNumber = "1234\nАА 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 4) passes the check successfully`(){
        makeTest(testNumber = "1234\nАА 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 4A) passes the check successfully`(){
        makeTest(testNumber = "АА 777\n1234", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 4B) passes the check successfully`(){
        makeTest(testNumber = "АА 12\nАА 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 5) passes the check successfully`(){
        makeTest(testNumber = "1234 АА 77", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 6) passes the check successfully`(){
        makeTest(testNumber = "АА 1234 77", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 7) passes the check successfully`(){
        makeTest(testNumber = "1234\nАА 77", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 8) passes the check successfully`(){
        makeTest(testNumber = "1234\nАА 77", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 20) passes the check successfully`(){
        makeTest(testNumber = "А 1234 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 21) passes the check successfully`(){
        makeTest(testNumber = "123 А 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier (type 22) passes the check successfully`(){
        makeTest(testNumber = "1234\nА 777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier with no spaces passes the check successfully`(){
        makeTest(testNumber = "А123АА777", expectedResult = true)
    }

    @Test
    fun `correct registration identifier with spaces passes the check successfully`(){
        makeTest(testNumber = "  А  123  АА  777  ", expectedResult = true)
    }

    @Test
    fun `registration identifier with more than one line break char doesn't pass the check`(){
        makeTest(testNumber = "12\nЧБ\n123456", expectedResult = false)
    }

    @Test
    fun `registration identifier with english chars doesn't pass the check`(){
        makeTest(testNumber = "А 123 YY 777", expectedResult = false)
    }

    @Test
    fun `registration identifier with unsuitable russian chars doesn't pass the check`(){
        makeTest(testNumber = "А 123 ГГ 777", expectedResult = false)
    }

    @Test
    fun `too long registration identifier doesn't pass the check`(){
        makeTest(testNumber = "    А    123    АА    777    ", expectedResult = false)
    }

    @Test
    fun `registration identifier with fully separated symbols doesn't pass the check`(){
        makeTest(testNumber = "А 1 2 3 А А 7 7 7", expectedResult = false)
    }
}