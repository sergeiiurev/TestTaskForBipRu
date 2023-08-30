package com.example.testtaskforbipru.domainLayer.usecases.utilities

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToCheck
import com.example.testtaskforbipru.domainLayer.entities.utilities.StringWithEnglishCharsToTranslate
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TranslateEnglishCharsToRussianOnesUseCaseTest {
    private var useCase = TranslateEnglishCharsToRussianOnesUseCase()

    private fun makeTest(testString: String, expectedResult: String){
        val actual = useCase.execute(StringWithEnglishCharsToTranslate(str = testString)).str
        Assertions.assertEquals(expectedResult, actual)
    }

    @AfterEach
    fun clearUseCase(){
        useCase = TranslateEnglishCharsToRussianOnesUseCase()
    }

    @Test
    fun `string containing only english chars translates successfully`(){
        makeTest(testString = "ABCDEFGY", expectedResult = "АВСDЕFGУ")
    }

    @Test
    fun `string containing only russian chars translates successfully`(){
        makeTest(testString = "АБВГД", expectedResult = "АБВГД")
    }

    @Test
    fun `string containing chars from both english and russian translates successfully`(){
        makeTest(testString = "fфYУ", expectedResult = "fфУУ")
    }

    @Test
    fun `empty string translates successfully`(){
        makeTest(testString = " ", expectedResult = " ")
    }
}