package com.br.appbusinesscard2.ui.adapter

import com.br.appbusinesscard2.domain.BusinessCard

sealed class DataItem {

    abstract val id: Long

    data class BusinessCardItem(val businessCard: BusinessCard, override val id: Long = businessCard.id) : DataItem()

    data class Header(val key: Char, override val id: Long = Long.MIN_VALUE) : DataItem()
}