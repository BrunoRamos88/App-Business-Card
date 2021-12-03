package com.br.appbusinesscard2.usecase

import androidx.lifecycle.LiveData
import com.br.appbusinesscard2.data.BusinessCardRepository
import com.br.appbusinesscard2.domain.BusinessCard

class ReadFromDatabaseUseCase (repository: BusinessCardRepository) {

    val listBusinessCard: LiveData<List<BusinessCard>> = repository.listBusinessCard
}

class RemoveFromDatabaseUseCase (private val repository: BusinessCardRepository) {

    suspend fun remove(businessCard: BusinessCard) {
        repository.remove(businessCard)
    }
}

class SaveToDatabaseUseCase (private val repository: BusinessCardRepository) {

    suspend fun saveNewCardToDatabase (businessCard: BusinessCard) {
        repository.save(businessCard)
    }

    suspend fun updateCardInDatabase (businessCard: BusinessCard) {
        repository.update(businessCard)
    }
}