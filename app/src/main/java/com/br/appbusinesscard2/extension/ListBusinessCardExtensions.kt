package com.br.appbusinesscard2.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.ui.adapter.DataItem

fun LiveData<List<BusinessCard>>.sortedByName(): LiveData<List<BusinessCard>> =
    this.map { list ->
        list.sortedBy { businessCard ->
            businessCard.name
        }
    }

fun List<BusinessCard>.toListOfDataItem(): List<DataItem> {

    val grouping = this.groupBy { businessCard ->
        businessCard.name.first()
    }

    val listDataItem = mutableListOf<DataItem>()
    grouping.forEach { mapEntry ->
        listDataItem.add(DataItem.Header(mapEntry.key))
        listDataItem.addAll(
            mapEntry.value.map { businessCard ->
                DataItem.BusinessCardItem(businessCard)
            }
        )
    }

    return listDataItem

}