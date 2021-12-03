package com.br.appbusinesscard2.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_businesscard")
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val phone: String,
    val email: String,
    val company: String,
    val customBackground: String
) : Parcelable