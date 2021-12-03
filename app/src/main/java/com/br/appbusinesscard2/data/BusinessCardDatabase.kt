package com.br.appbusinesscard2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.appbusinesscard2.domain.BusinessCard

@Database(entities = [BusinessCard::class], version = 1, exportSchema = false)
abstract class BusinessCardDatabase : RoomDatabase() {

    abstract val businessCardDao : BusinessCardDao

    companion object {

        @Volatile
        private var INSTANCE: BusinessCardDatabase? = null

        fun getInstance(context: Context) : BusinessCardDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BusinessCardDatabase::class.java,
                        "businesscard_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}