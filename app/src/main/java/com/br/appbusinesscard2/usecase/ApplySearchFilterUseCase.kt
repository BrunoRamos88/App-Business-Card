package com.br.appbusinesscard2.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.br.appbusinesscard2.domain.BusinessCard

class ApplySearchFilterUseCase {

    fun filterList(
        list: LiveData<List<BusinessCard>>,
        searchQuery: LiveData<CharSequence>
    ) : LiveData<List<BusinessCard>> = Transformations.switchMap(searchQuery) {
        list.map { list ->
            searchQuery.value?.let {
                list.filter { businessCard ->
                    val query = searchQuery.value.toString()
                    with(businessCard) {
                        applyQuery(query)
                    }
                }
            }
        }
    }

    private fun BusinessCard.applyQuery(query: String) =
        company.contains(query, true) ||
                name.contains(query, true) ||
                email.contains(query, true)
}