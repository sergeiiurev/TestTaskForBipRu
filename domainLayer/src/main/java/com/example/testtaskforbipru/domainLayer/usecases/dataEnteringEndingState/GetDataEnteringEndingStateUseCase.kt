package com.example.testtaskforbipru.domainLayer.usecases.dataEnteringEndingState

import com.example.testtaskforbipru.domainLayer.entities.dataEnteringEndingState.ReceivedDataEnteringEndingState

class GetDataEnteringEndingStateUseCase(private val repo: DataEnteringEndingStateRepository) {
    fun execute(): ReceivedDataEnteringEndingState {
        return repo.getDataEnteringEndingState()
    }
}