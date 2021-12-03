package com.br.appbusinesscard2.ui.adapter

import android.view.View
import com.br.appbusinesscard2.domain.BusinessCard

class BusinessCardListener(
    val clickListener: (businessCard: BusinessCard) -> Unit,
    val shareBtnClickListener: (view: View) -> Unit,
    val longClickListener: (businessCard: BusinessCard) -> Boolean
) {

    fun onClick(businessCard: BusinessCard) {
        clickListener(businessCard)
    }

    fun onLongClick(businessCard: BusinessCard) : Boolean {
        longClickListener(businessCard)
        return true
    }

    fun onShareBtnClick(view: View) {
        shareBtnClickListener(view)
    }

}