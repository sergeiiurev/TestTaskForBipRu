package com.example.testtaskforbipru.presentationLayer.firstEmptyActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testtaskforbipru.presentationLayer.finalResultActivity.FinalResultActivity
import com.example.testtaskforbipru.presentationLayer.regIdentifierActivity.VehicleRegIdentifierActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(vm.dataEnteringEndingState){
            startActivity(
                Intent(this, FinalResultActivity::class.java)
                    // Deletes this empty Activity from Task so started activity will become root
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        } else {
            startActivity(
                Intent(this, VehicleRegIdentifierActivity::class.java)
                    // Deletes this empty Activity from Task so started activity will become root
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
        finish()
    }

}