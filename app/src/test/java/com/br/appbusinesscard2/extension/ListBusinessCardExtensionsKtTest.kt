package com.br.appbusinesscard2.extension

import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.ui.adapter.DataItem
import org.junit.Test

class ListBusinessCardExtensionsKtTest {

    val listOfBusinessCard = listOf(
        BusinessCard(
            name = "John Snow",
            phone = "(+11) 9898-9999",
            email = "johnsnow19@aol.com",
            company = "Winter Company LTDA",
            customBackground = "#FF000030"
        ),
        BusinessCard(
            name = "Daenerys Targeryen",
            phone = "(+999) 7799-1122",
            email = "falida@aol.com",
            company = "Winter Company LTDA",
            customBackground = "#FF8f216E"
        )
    )

    @Test
    fun addHeadersBusinessCardListReceiver() {

        val listDataItem: List<DataItem> = listOfBusinessCard.toListOfDataItem()
        println(listDataItem)
        assert(listDataItem.size == 4)
    }
}