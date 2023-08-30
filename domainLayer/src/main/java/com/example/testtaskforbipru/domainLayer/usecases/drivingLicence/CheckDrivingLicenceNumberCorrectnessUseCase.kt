package com.example.testtaskforbipru.domainLayer.usecases.drivingLicence

import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberCheckingResult
import com.example.testtaskforbipru.domainLayer.entities.drivingLicence.DrivingLicenceNumberToCheck

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
class CheckDrivingLicenceNumberCorrectnessUseCase {
    private val validRussianChars = "АБВЕКМНОРСТУХЧ"
    private val emptyChars = "([\\t ]*)"

    private val consistsOfOnlyRussianCharsRegex = "[${validRussianChars}\\d \\n]*".toRegex()
    private val correspondsToLicenceCharsFormatRegex = "^$emptyChars(\\d{2})$emptyChars([$validRussianChars]{2})$emptyChars(\\d{6})$emptyChars$".toRegex()
    private val correspondsToLicenceDigitsFormatRegex = "^$emptyChars(\\d{2})$emptyChars(\\d{2})$emptyChars(\\d{6})$emptyChars$".toRegex()

    fun execute(drivingLicenceNumber: DrivingLicenceNumberToCheck): DrivingLicenceNumberCheckingResult {
        val currNumber = drivingLicenceNumber.drivingLicenceNumber
        // Fast correctness check
        if((currNumber.length > MAX_STRING_LENGTH_IN_EDIT_TEXT) or
            (!currNumber.matches(consistsOfOnlyRussianCharsRegex)) or
            (currNumber.contains('\n'))){
            return DrivingLicenceNumberCheckingResult(
                result = false
            )
        }
        return DrivingLicenceNumberCheckingResult(
            result = (currNumber.matches(correspondsToLicenceCharsFormatRegex)) or
                    (currNumber.matches(correspondsToLicenceDigitsFormatRegex))
        )
    }
}