package com.example.testtaskforbipru.presentationLayer.firstEmptyActivity

import androidx.lifecycle.ViewModel
import com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState.GetDataEnteringEndingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(getDataEnteringCompletenessUC: GetDataEnteringEndingStateUseCase): ViewModel() {
    val dataEnteringEndingState = getDataEnteringCompletenessUC.execute().state
}