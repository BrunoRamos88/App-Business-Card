package com.br.appbusinesscard2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.br.appbusinesscard2.databinding.ItemBusinesscardBinding
import com.br.appbusinesscard2.databinding.ItemHeaderBinding
import com.br.appbusinesscard2.domain.BusinessCard

sealed class DataItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root){

    class BusinessCardViewHolder(val binding: ItemBusinesscardBinding) :
        DataItemViewHolder(binding) {

        fun bind(item: BusinessCard, cardListener: BusinessCardListener) {
            with(binding) {
                businessCard = item
                listener = cardListener
                executePendingBindings()
            }
        }

        companion object {

            fun from(parent: ViewGroup) : BusinessCardViewHolder {
                val binding: ItemBusinesscardBinding = ItemBusinesscardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BusinessCardViewHolder(binding)
            }
        }
    }

    class HeaderViewHolder(val binding: ItemHeaderBinding) :
        DataItemViewHolder(binding) {

        fun bind(item: DataItem.Header) {
            with(binding) {
                header = item
                executePendingBindings()
            }
        }

        companion object {

            fun from(parent: ViewGroup) : HeaderViewHolder {
                val binding: ItemHeaderBinding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return HeaderViewHolder(binding)
            }
        }
    }
}