package com.br.appbusinesscard2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br.appbusinesscard2.domain.BusinessCard

@Dao
interface BusinessCardDao {

    @Insert
    suspend fun insert(businessCard: BusinessCard)

    @Query("SELECT * FROM table_businesscard")
    fun getAll() : LiveData<List<BusinessCard>>

    @Delete
    suspend fun remove(businessCard: BusinessCard)

    @Query("SELECT * FROM table_businesscard WHERE id = :key")
    fun get(key: Long) : BusinessCard?

    @Update
    suspend fun update(businessCard: BusinessCard)


}