package com.example.testtaskforbipru.presentationLayer.drivingLicenceActivity

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
import com.example.testtaskforbipru.databinding.ActivityDrivingLicenceBinding
import com.example.testtaskforbipru.presentationLayer.finalResultActivity.FinalResultActivity
import dagger.hilt.android.AndroidEntryPoint

private const val MAX_STRING_LENGTH_IN_EDIT_TEXT = 20
@AndroidEntryPoint
class DrivingLicenceActivity : AppCompatActivity() {
    private val vm: DrivingLicenceVM by viewModels()
    private val binding by lazy {ActivityDrivingLicenceBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val drivingLicenceET = binding.drivingLicenceET

        drivingLicenceET.addTextChangedListener(object: TextWatcher {
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
                    getString(R.string.available_for_driving_licence_input_russian_chars)}\\d${
                    getString(R.string.available_for_input_english_chars)} \\n]*".toRegex()
                )
                val consistsOfOnlyRussianChars = currStr.matches("[${
                    getString(R.string.available_for_driving_licence_input_russian_chars)}\\d \\n]*".toRegex()
                )

                // Length and lines count are separated from Regex to make code easier to read
                if((currStr.contains('\n')) or (currStr.length > MAX_STRING_LENGTH_IN_EDIT_TEXT)){
                    shouldDeleteLastInput = true
                    Toast.makeText(drivingLicenceET.context,
                        getString(R.string.incorrect_format_of_input_message), Toast.LENGTH_LONG).show()
                } else if (!consistsOfOnlyValidChars) {
                    shouldDeleteLastInput = true
                    Toast.makeText(drivingLicenceET.context,
                        getString(R.string.input_contains_incorrect_symbols_message), Toast.LENGTH_LONG).show()
                } else if(!consistsOfOnlyRussianChars){
                    vm.translateEnglishCharsToRussianOnes(currStr)
                } else {
                    // This "if" is for preventing use case run when EditText returns to previous text
                    if(!shouldDeleteLastInput) {
                        vm.checkDrivingLicenceCorrectness(currStr)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
                if(shouldIgnoreTextChanges){
                    return
                }

                if(shouldDeleteLastInput){
                    shouldIgnoreTextChanges = true

                    val countOfRemovedChars = drivingLicenceET.text.length - lastTextReturnToInEditText.length
                    userCursorLocation = drivingLicenceET.selectionStart
                    drivingLicenceET.setText(lastTextReturnToInEditText)
                    drivingLicenceET.setSelection(userCursorLocation-countOfRemovedChars)
                    userCursorLocation = 0
                    shouldDeleteLastInput = false

                    shouldIgnoreTextChanges = false
                }
            }
        })
        vm.enteredDrivingLicenceCorrectnessLiveData.observe(this){
            if(it == true){
                binding.drivingLicenceCorrectnessTV.text =
                    getString(R.string.driving_licence_number_is_correct)
            } else {
                binding.drivingLicenceCorrectnessTV.text =
                    getString(R.string.driving_licence_number_is_incorrect)
            }
        }
        vm.stringTranslatedToRussian.observe(this){
            val userCursorLocation = drivingLicenceET.selectionStart
            drivingLicenceET.setText(it)
            drivingLicenceET.setSelection(userCursorLocation)
        }
        // Set last saved correct number (if exists) when Activity created from scratch (for user convenience)
        if(vm.thisActivityCreatedFromScratch)
            drivingLicenceET.setText(vm.lastSavedCorrectNumber)

        binding.goToNextScreenBtn3.setOnClickListener{
            if(vm.enteredDrivingLicenceCorrectnessLiveData.value == true){
                vm.saveDrivingLicenceNumber(drivingLicenceET.text.toString())
                vm.drivingLicenceWasSuccessfullySaved.observe(this){
                    vm.drivingLicenceWasSuccessfullySaved.removeObservers(this)
                    if(it == true){
                        startActivity(Intent(this, FinalResultActivity::class.java))
                    } else {
                        Toast.makeText(
                            drivingLicenceET.context,
                            getString(R.string.data_saving_error_message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle(getString(R.string.skipping_input_dialog_title))
                    .setMessage(getString(R.string.skipping_driving_licence_input_dialog_message))
                    .setPositiveButton(getString(R.string.skipping_driving_licence_input_dialog_positive_btn)){ _: DialogInterface, _: Int ->
                        startActivity(Intent(this, FinalResultActivity::class.java))
                    }.setNegativeButton(getString(R.string.skipping_driving_licence_input_dialog_negative_btn)){ _: DialogInterface, _: Int -> }
                    .create()
                    .show()
            }
        }
    }
}