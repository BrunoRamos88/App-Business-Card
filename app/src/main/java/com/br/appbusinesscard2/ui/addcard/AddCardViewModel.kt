package com.br.appbusinesscard2.ui.addcard

import androidx.lifecycle.*
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.domain.BusinessCardValidation
import com.br.appbusinesscard2.domain.Either
import com.br.appbusinesscard2.usecase.SaveToDatabaseUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class AddCardViewModel(private val saveToDatabaseUseCase: SaveToDatabaseUseCase) : ViewModel() {

    private val _newCard = MutableLiveData<BusinessCard>().apply { value = null }
    val newCard: LiveData<BusinessCard>
        get() = _newCard

    private val _receivedCard = MutableLiveData<BusinessCard>(null)
    val receivedCard: BusinessCard?
        get() = _receivedCard.value

    val showEditLabel = Transformations.map(_receivedCard) {
        it != null
    }

    private var _errorSnackbar = MutableLiveData<String?>(null)
    val errorSnackbar: LiveData<String?>
        get() = _errorSnackbar


    val nameField = MutableLiveData("")
    val phoneField = MutableLiveData("")
    val emailField = MutableLiveData("")
    val companyField = MutableLiveData("")
    val customBackgroundField = MutableLiveData("")


    fun createNewCard() {

        val mBusinessCard = BusinessCard(
            name = nameField.value.toString(),
            phone = phoneField.value.toString(),
            email = emailField.value.toString(),
            company = companyField.value.toString(),
            customBackground = customBackgroundField.value.toString()
        )

        val either = BusinessCardValidation.validate(mBusinessCard) as Either.BusinessCardEither
        with(either) {
            businessCard?.let {
                _newCard.value = it
                saveCard()
            }
            exception?.let {
                _errorSnackbar.value = it.message
                errorSnackBarShown()
            }
        }
    }

    private fun errorSnackBarShown() {
        _errorSnackbar.value = null
    }

    fun setCustomBackground(color: String) {
        customBackgroundField.value = color
    }

    private fun saveCard() {
        if (receivedCard != null) {
            launchDataSave {
                newCard.value?.let {
                    saveToDatabaseUseCase.updateCardInDatabase(
                        it.copy(id = receivedCard?.id!!))
                }
            }
        } else {
            launchDataSave {
                newCard.value?.let {
                    saveToDatabaseUseCase.saveNewCardToDatabase(it)
                }
            }
        }

        doneNavigateToHomeFragment()
    }

    private fun doneNavigateToHomeFragment() {
        _newCard.value = null
    }

    private fun launchDataSave(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            }
            catch (ex: Exception) {
                _errorSnackbar.value = ex.message
            }
        }
    }

    fun receivedCard(businessCard: BusinessCard) {
        with(businessCard) {
            _receivedCard.value = businessCard
            initFields(this)
        }
    }

    private fun initFields(businessCard: BusinessCard) {
        with(businessCard) {
            nameField.value = name
            phoneField.value = phone
            emailField.value = email
            companyField.value = company
        }
    }

}