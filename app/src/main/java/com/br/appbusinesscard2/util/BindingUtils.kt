package com.br.appbusinesscard2.util

import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.br.appbusinesscard2.R
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.ui.adapter.DataItem
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("Text View Nome")
fun MaterialTextView.setName(item: BusinessCard?) {
    item?.let { businessCard ->
        text = businessCard.name
    }
}

@BindingAdapter("Text View Telefone")
fun MaterialTextView.setPhone(item: BusinessCard?) {
    item?.let { businessCard ->
        text = businessCard.phone
    }
}

@BindingAdapter("Text View Email")
fun MaterialTextView.setEmail(item: BusinessCard?) {
    item?.let { businessCard ->
        text = businessCard.email
    }
}

@BindingAdapter("Text View Empresa")
fun MaterialTextView.setCompany(item: BusinessCard?) {
    item?.let { businessCard ->
        text = businessCard.company
    }
}
@BindingAdapter("Fundo Personalizado")
fun MaterialCardView.setItemBackgroundColor(item: BusinessCard?) {
    item?.let {
        setCardBackgroundColor(Color.parseColor(item.customBackground))
    }
}

@BindingAdapter("Card From Fragment Label")
fun MaterialTextView.setLabel(isEdit: Boolean) {
    isEdit.let {
        if (!it) {
            text = context.getString(R.string.description_add_card_fragment)
        }
        else {
            text = context.getString(R.string.description_edit_card_fragment)
        }
    }
}

@BindingAdapter("Card Reader Key")
fun MaterialTextView.setKey(item: DataItem.Header) {
    item.let {
        text = item.key.toString()
    }
}