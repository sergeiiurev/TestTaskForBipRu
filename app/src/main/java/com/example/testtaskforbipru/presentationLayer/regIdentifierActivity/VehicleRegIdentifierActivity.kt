package com.example.testtaskforbipru.presentationLayer.regIdentifierActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testtaskforbipru.R
import com.example.testtaskforbipru.databinding.ActivityVehicleRegistrationIdentifierBinding
import com.example.testtaskforbipru.presentationLayer.drivingLicenceActivity.DrivingLicenceActivity
import com.example.testtaskforbipru.presentationLayer.regCertificateActivity.VehicleRegCertificateActivity
import dagger.hilt.android.AndroidEntryPoint

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
private const val MAX_LINES_COUNT_IN_EDIT_TEXT = 2
@AndroidEntryPoint
class VehicleRegIdentifierActivity : AppCompatActivity() {
    private val vm: VehicleRegIdentifierVM by viewModels()
    private val binding by lazy {ActivityVehicleRegistrationIdentifierBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val regIdentifierET = binding.vehicleRegIdentifierET

        regIdentifierET.addTextChangedListener(object: TextWatcher {
            private var shouldDeleteLastInput = false
            private var shouldIgnoreTextChanges = false
            private var lastTextReturnToInEditText = ""
            private var userCursorLocation = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                lastTextReturnToInEditText = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currStr = s.toString()
                val consistsOfOnlyValidChars = currStr.matches("[${
                    getString(R.string.available_for_identifier_input_russian_chars)}\\d${
                        getString(R.string.available_for_input_english_chars)} \\n]*".toRegex()
                )
                val consistsOfOnlyRussianChars = currStr.matches("[${
                    getString(R.string.available_for_identifier_input_russian_chars)}\\d \\n]*".toRegex()
                )

                // Length and lines count are separated from Regex to make code easier to read
                if((currStr.count { it == '\n' } >= MAX_LINES_COUNT_IN_EDIT_TEXT) or (currStr.length > MAX_STRING_LENGTH_IN_EDIT_TEXT)){
                    shouldDeleteLastInput = true
                    Toast.makeText(
                        regIdentifierET.context,
                        getString(R.string.incorrect_format_of_input_message),
                        Toast.LENGTH_LONG
                    ).show()
                } else if (!consistsOfOnlyValidChars) {
                    shouldDeleteLastInput = true
                    Toast.makeText(
                        regIdentifierET.context,
                        getString(R.string.input_contains_incorrect_symbols_message),
                        Toast.LENGTH_LONG
                    ).show()
                } else if(!consistsOfOnlyRussianChars){
                    vm.translateEnglishCharsToRussianOnes(currStr)
                } else {
                    // This "if" is for preventing use case run when EditText returns to previous text
                    if(!shouldDeleteLastInput) {
                        vm.checkIdentifierCorrectness(currStr)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
                if(shouldIgnoreTextChanges){
                    return
                }

                if(shouldDeleteLastInput){
                    shouldIgnoreTextChanges = true

                    val countOfRemovedChars = regIdentifierET.text.length - lastTextReturnToInEditText.length
                    userCursorLocation = regIdentifierET.selectionStart
                    regIdentifierET.setText(lastTextReturnToInEditText)
                    regIdentifierET.setSelection(userCursorLocation-countOfRemovedChars)
                    userCursorLocation = 0
                    shouldDeleteLastInput = false

                    shouldIgnoreTextChanges = false
                }
            }
        })
        vm.enteredIdentifierCorrectnessLiveData.observe(this){
            if(it == true){
                binding.vehicleRegIdentifierCorrectnessTV.text = getString(R.string.identifier_number_is_correct)
            } else {
                binding.vehicleRegIdentifierCorrectnessTV.text = getString(R.string.identifier_number_is_incorrect)
            }
        }
        vm.stringTranslatedToRussian.observe(this){
            val userCursorLocation = regIdentifierET.selectionStart
            regIdentifierET.setText(it)
            regIdentifierET.setSelection(userCursorLocation)
        }
        // Set last saved correct number (if exists) when Activity created from scratch (for user convenience)
        if(vm.thisActivityCreatedFromScratch)
            regIdentifierET.setText(vm.lastSavedCorrectNumber)


        binding.goToNextScreenBtn1.setOnClickListener {
            if(vm.enteredIdentifierCorrectnessLiveData.value == true){
                vm.saveIdentifierNumber(regIdentifierET.text.toString())
                vm.identifierWasSuccessfullySaved.observe(this){
                    vm.identifierWasSuccessfullySaved.removeObservers(this)
                    if(it == true){
                        startActivity(Intent(this, VehicleRegCertificateActivity::class.java))
                    } else {
                        Toast.makeText(
                            regIdentifierET.context,
                            getString(R.string.data_saving_error_message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle(getString(R.string.skipping_input_dialog_title))
                    .setMessage(getString(R.string.skipping_identifier_input_dialog_message))
                    .setPositiveButton(getString(R.string.skipping_identifier_input_dialog_positive_btn)){ _: DialogInterface, _: Int ->
                        vm.saveVehicleRegInfoEnteringIsSkippedState(state = true)
                        vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.observe(this){
                            vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.removeObservers(this)
                            if(it == true){
                                startActivity(Intent(this, DrivingLicenceActivity::class.java))
                            } else {
                                Toast.makeText(
                                    regIdentifierET.context,
                                    getString(R.string.data_saving_error_message),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }.setNegativeButton(getString(R.string.skipping_identifier_input_dialog_negative_btn)){ _: DialogInterface, _: Int -> }
                    .create()
                    .show()
            }
        }
    }
}