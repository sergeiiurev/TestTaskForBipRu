package com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState

import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateSavingResult
import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.DataEnteringEndingStateToSave

class SaveDataEnteringEndingStateUseCase(private val repo: DataEnteringEndingStateRepository) {
    fun execute(dataEnteringEndingState: DataEnteringEndingStateToSave): DataEnteringEndingStateSavingResult {
        return repo.saveDataEnteringEndingState(state = dataEnteringEndingState)
    }
}