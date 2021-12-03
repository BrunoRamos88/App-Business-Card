package com.br.appbusinesscard2.ui.home

import android.view.View
import androidx.lifecycle.*
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.extension.sortedByName
import com.br.appbusinesscard2.usecase.ApplySearchFilterUseCase
import com.br.appbusinesscard2.usecase.ReadFromDatabaseUseCase
import com.br.appbusinesscard2.usecase.RemoveFromDatabaseUseCase
import kotlinx.coroutines.launch

class HomeViewModel(readFromDatabaseUseCase: ReadFromDatabaseUseCase,
                    private val removeFromDatabaseUseCase: RemoveFromDatabaseUseCase,
                    applySearchFilterUseCase: ApplySearchFilterUseCase
) : ViewModel() {

    private val _listBusinessCard: LiveData<List<BusinessCard>> =
        readFromDatabaseUseCase.listBusinessCard

    val listBusinessCard: LiveData<List<BusinessCard>>
        get() = _listBusinessCard

    val showEmptyListMessage = Transformations.map(_listBusinessCard) {
        if (it.isEmpty()) {
            return@map View.VISIBLE
        } else return@map View.GONE
    }

    private val _searchQuery = MutableLiveData<CharSequence>("")
    val searchQuery: LiveData<CharSequence>
        get() = _searchQuery

    fun setSearchQuery(query: CharSequence?) {
        query?.let {
            _searchQuery.value = it
        }
    }

    val filteredListBusinessCard: LiveData<List<BusinessCard>> =
        applySearchFilterUseCase.filterList(listBusinessCard, searchQuery).sortedByName()

    val navigateToAddCardFragment = MutableLiveData(false)

    fun navigateToAddCardFragment() {
        navigateToAddCardFragment.value = true
    }

    fun doneNavigateToAddCardFragment() {
        navigateToAddCardFragment.value = false
    }

    fun remove(businessCard: BusinessCard) {
        viewModelScope.launch {
            removeFromDatabaseUseCase.remove(businessCard)
        }
    }
}