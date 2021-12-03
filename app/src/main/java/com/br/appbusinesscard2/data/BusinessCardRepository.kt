package com.br.appbusinesscard2.data

import androidx.lifecycle.LiveData
import com.br.appbusinesscard2.domain.BusinessCard

class BusinessCardRepository(private val dao: BusinessCardDao) {
    /*
        fun insert(businessCard: BusinessCard) = runBlocking {
            launch(Dispatchers.IO) {
                dao.insert(businessCard)
            }
        }
     */
    val listBusinessCard: LiveData<List<BusinessCard>>
        get() = dao.getAll()
    //fun getAll() = dao.getAll()

    suspend fun save(businessCard: BusinessCard) {
        dao.insert(businessCard)
    }

    suspend fun remove(businessCard: BusinessCard) {
        dao.remove(businessCard)
    }

    fun get(cardId: Long) : BusinessCard? = dao.get(cardId)

    suspend fun update(businessCard: BusinessCard) {
        dao.update(businessCard)
    }

}