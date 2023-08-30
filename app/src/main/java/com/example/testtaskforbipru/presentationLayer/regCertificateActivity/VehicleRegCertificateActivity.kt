package com.example.testtaskforbipru.presentationLayer.regCertificateActivity

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
import com.example.testtaskforbipru.databinding.ActivityVehicleRegCertificateBinding
import com.example.testtaskforbipru.presentationLayer.drivingLicenceActivity.DrivingLicenceActivity
import dagger.hilt.android.AndroidEntryPoint

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
@AndroidEntryPoint
class VehicleRegCertificateActivity : AppCompatActivity() {
    private val vm: VehicleRegCertificateVM by viewModels()
    private val binding by lazy {ActivityVehicleRegCertificateBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val regCertificateET = binding.vehicleRegCertificateET

        regCertificateET.addTextChangedListener(object: TextWatcher {
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
                    getString(R.string.available_for_certificate_input_russian_chars)}\\d${
                        getString(R.string.available_for_input_english_chars)} \\n]*".toRegex()
                )
                val consistsOfOnlyRussianChars = currStr.matches("[${
                    getString(R.string.available_for_certificate_input_russian_chars)}\\d \\n]*".toRegex()
                )

                // Length and lines count are separated from Regex to make Regexes easier to read
                if((currStr.contains('\n')) or (currStr.length > MAX_STRING_LENGTH_IN_EDIT_TEXT)){
                    shouldDeleteLastInput = true
                    Toast.makeText(regCertificateET.context, getString(R.string.incorrect_format_of_input_message), Toast.LENGTH_LONG).show()
                } else if (!consistsOfOnlyValidChars) {
                    shouldDeleteLastInput = true
                    Toast.makeText(regCertificateET.context, getString(R.string.input_contains_incorrect_symbols_message), Toast.LENGTH_LONG).show()
                } else if(!consistsOfOnlyRussianChars){
                    vm.translateEnglishCharsToRussianOnes(currStr)
                } else {
                    // This "if" is for preventing use case run when EditText returns to previous text
                    if(!shouldDeleteLastInput) {
                        vm.checkCertificateCorrectness(currStr)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
                if(shouldIgnoreTextChanges){
                    return
                }

                if(shouldDeleteLastInput){
                    shouldIgnoreTextChanges = true

                    val countOfRemovedChars = regCertificateET.text.length - lastTextReturnToInEditText.length
                    userCursorLocation = regCertificateET.selectionStart
                    regCertificateET.setText(lastTextReturnToInEditText)
                    regCertificateET.setSelection(userCursorLocation-countOfRemovedChars)
                    userCursorLocation = 0
                    shouldDeleteLastInput = false

                    shouldIgnoreTextChanges = false
                }
            }

        })
        vm.enteredCertificateCorrectnessLiveData.observe(this){
            if(it == true){
                binding.vehicleRegCertificateCorrectnessTV.text = getString(R.string.certificate_number_is_correct)
            } else {
                binding.vehicleRegCertificateCorrectnessTV.text = getString(R.string.certificate_number_is_incorrect)
            }
        }
        vm.stringTranslatedToRussian.observe(this){
            val userCursorLocation = regCertificateET.selectionStart
            regCertificateET.setText(it)
            regCertificateET.setSelection(userCursorLocation)
        }
        // Set last saved correct number (if exists) when Activity created from scratch (for user convenience)
        if(vm.thisActivityCreatedFromScratch)
            regCertificateET.setText(vm.lastSavedCorrectNumber)

        binding.goToNextScreenBtn2.setOnClickListener{
            if(vm.enteredCertificateCorrectnessLiveData.value == true){
                vm.saveCertificateNumber(regCertificateET.text.toString())
                vm.certificateWasSuccessfullySaved.observe(this){
                    vm.certificateWasSuccessfullySaved.removeObservers(this)
                    if(it == true){
                        vm.saveVehicleRegInfoEnteringIsSkippedState(state = false)
                        vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.observe(this){ result: Boolean ->
                            vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.removeObservers(this)
                            if(result){
                                startActivity(Intent(this, DrivingLicenceActivity::class.java))
                            } else {
                                Toast.makeText(
                                    regCertificateET.context,
                                    getString(R.string.data_saving_error_message),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            regCertificateET.context,
                            getString(R.string.data_saving_error_message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle(getString(R.string.skipping_input_dialog_title))
                    .setMessage(getString(R.string.skipping_certificate_input_dialog_message))
                    .setPositiveButton(getString(R.string.skipping_certificate_input_dialog_positive_btn)){ _: DialogInterface, _: Int ->
                        vm.saveVehicleRegInfoEnteringIsSkippedState(state = true)
                        vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.observe(this){
                            vm.vehicleRegInfoEnteringIsSkippedStateWasSuccessfullySaved.removeObservers(this)
                            if(it == true){
                                startActivity(Intent(this, DrivingLicenceActivity::class.java))
                            } else {
                                Toast.makeText(
                                    regCertificateET.context,
                                    getString(R.string.data_saving_error_message),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }.setNegativeButton(getString(R.string.skipping_certificate_input_dialog_neagtive_btn)){ _: DialogInterface, _: Int -> }
                    .create()
                    .show()
            }
        }
    }
}