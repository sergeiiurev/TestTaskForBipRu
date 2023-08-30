package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationIdentifier

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberCheckingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToCheck

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
private const val MAX_LINES_COUNT_IN_EDIT_TEXT = 2
class CheckVehicleRegIdentifierCorrectnessUseCase {
    private val validRussianChars = "АВЕКМНОРСТУХ"
    private val region = "(\\d{2,3})"
    private val militaryDistrict = "(\\d{2})"
    private val emptyChars = "([\\t ]*)"


    private val consistsOfOnlyRussianCharsRegex = "[${validRussianChars}\\d \\n]*".toRegex()
    //region group 1
    private val correspondsToType1A = "^$emptyChars([$validRussianChars])$emptyChars(\\d{3})$emptyChars([$validRussianChars]{2})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType1B = "^$emptyChars([$validRussianChars]{2})$emptyChars(\\d{3})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType2 = "^$emptyChars([$validRussianChars]{2})$emptyChars(\\d{4})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType3 = "^$emptyChars(\\d{4})$emptyChars\\n$emptyChars([$validRussianChars]{2})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType4 = "^$emptyChars(\\d{4})$emptyChars\\n$emptyChars([$validRussianChars]{2})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType4A = "^$emptyChars([$validRussianChars]{2})$emptyChars$region$emptyChars\\n$emptyChars(\\d{4})$emptyChars$".toRegex()
    private val correspondsToType4B = "^$emptyChars([$validRussianChars]{2})$emptyChars(\\d{2})$emptyChars\\n$emptyChars([$validRussianChars]{2})$emptyChars$region$emptyChars$".toRegex()
    //endregion
    //region group 2
    private val correspondsToType5 = "^$emptyChars(\\d{4})$emptyChars([$validRussianChars]{2})$emptyChars$militaryDistrict$emptyChars$".toRegex()
    private val correspondsToType6 = "^$emptyChars([$validRussianChars]{2})$emptyChars(\\d{4})$emptyChars$militaryDistrict$emptyChars$".toRegex()
    private val correspondsToType7 = "^$emptyChars(\\d{4})$emptyChars\\n$emptyChars([$validRussianChars]{2})$emptyChars$militaryDistrict$emptyChars$".toRegex()
    private val correspondsToType8 = "^$emptyChars(\\d{4})$emptyChars\\n$emptyChars([$validRussianChars]{2})$emptyChars$militaryDistrict$emptyChars$".toRegex()
    //endregion
    //region group 5
    private val correspondsToType20 = "^$emptyChars([$validRussianChars])$emptyChars(\\d{4})$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType21 = "^$emptyChars(\\d{3})$emptyChars([$validRussianChars])$emptyChars$region$emptyChars$".toRegex()
    private val correspondsToType22 = "^$emptyChars(\\d{4})$emptyChars\\n$emptyChars([$validRussianChars])$emptyChars$region$emptyChars$".toRegex()
    //endregion
    private val allIdentifierTypesRegexes = arrayOf(correspondsToType1A, correspondsToType1B, correspondsToType2,
        correspondsToType3, correspondsToType4, correspondsToType4A, correspondsToType4B, correspondsToType5,
        correspondsToType6, correspondsToType7, correspondsToType8, correspondsToType20, correspondsToType21,
        correspondsToType22)

    fun execute(regIdentifier: com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberToCheck): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberCheckingResult {
        val currNumber = regIdentifier.identifierNumber
        // Fast correctness check
        if((currNumber.count { it == '\n' } >= MAX_LINES_COUNT_IN_EDIT_TEXT) or
            (currNumber.length > MAX_STRING_LENGTH_IN_EDIT_TEXT) or
            (!currNumber.matches(consistsOfOnlyRussianCharsRegex))){
            return com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberCheckingResult(
                result = false
            )
        }

        for(typeRegex in allIdentifierTypesRegexes){
            if(currNumber.matches(typeRegex)){
                return com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberCheckingResult(
                    result = true
                )
            }
        }
        return com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationIdentifier.RegIdentifierNumberCheckingResult(
            result = false
        )
    }
}