package com.example.testtaskforbipru.domainLayer.usecases.vehicleRegistrationCertificate

import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberCheckingResult
import com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToCheck

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
class CheckVehicleRegCertificateCorrectnessUseCase {
    private val validRussianChars = "АБВЕЗКМНОРСТУХ"
    private val emptyChars = "([\\t ]*)"

    private val consistsOfOnlyRussianCharsRegex = "[${validRussianChars}\\d \\n]*".toRegex()
    private val correspondsToCertificateCharsFormatRegex = "^$emptyChars(\\d{2})$emptyChars([$validRussianChars]{2})$emptyChars(\\d{6})$emptyChars$".toRegex()
    private val correspondsToCertificateDigitsFormatRegex = "^$emptyChars(\\d{2})$emptyChars(\\d{2})$emptyChars(\\d{6})$emptyChars$".toRegex()

    fun execute(regCertificate: com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberToCheck): com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberCheckingResult {
        val currNumber = regCertificate.certificateNumber
        // Fast correctness check
        if((currNumber.length > MAX_STRING_LENGTH_IN_EDIT_TEXT) or
            (!currNumber.matches(consistsOfOnlyRussianCharsRegex)) or
            (currNumber.contains('\n'))){
            return com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberCheckingResult(
                result = false
            )
        }

        return com.example.testtaskforbipru.domainLayer.entities.vehicleRegistrationCertificate.RegCertificateNumberCheckingResult(
            result = (currNumber.matches(correspondsToCertificateCharsFormatRegex)) or
                    (currNumber.matches(correspondsToCertificateDigitsFormatRegex))
        )
    }
}