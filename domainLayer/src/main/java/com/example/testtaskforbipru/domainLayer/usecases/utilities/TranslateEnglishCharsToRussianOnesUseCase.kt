package com.example.testtaskforbipru.domainLayer.usecases.utilities

import com.example.testtaskforbipru.domainLayer.entities.utilities.StringWithEnglishCharsToTranslate
import com.example.testtaskforbipru.domainLayer.entities.utilities.StringWithTranslatedChars

class TranslateEnglishCharsToRussianOnesUseCase {
    fun execute(strWithEnglishChars: StringWithEnglishCharsToTranslate): StringWithTranslatedChars {
        val englishChars = arrayOf('A','B','E','K','M','H','O','P','C','T','Y','X')
        val russianChars = arrayOf('А','В','Е','К','М','Н','О','Р','С','Т','У','Х')
        var tempTranslatedString = strWithEnglishChars.str
        for(i in englishChars.indices){
            tempTranslatedString = tempTranslatedString.replace(oldChar = englishChars[i], newChar = russianChars[i])
        }
        return StringWithTranslatedChars(
            tempTranslatedString
        )
    }
}