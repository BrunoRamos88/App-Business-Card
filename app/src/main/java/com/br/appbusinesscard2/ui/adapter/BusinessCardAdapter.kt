package com.br.appbusinesscard2.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.extension.toListOfDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

class BusinessCardAdapter(val cardListener: BusinessCardListener) : ListAdapter<DataItem,
        DataItemViewHolder>(DiffCallback()) {


    private val adapterScope = CoroutineScope(Dispatchers.Default)


    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataItemViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> DataItemViewHolder.HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> DataItemViewHolder.BusinessCardViewHolder.from(parent)
            else -> throw ClassCastException("ViewType desconhecido ${viewType}")
        }
    }


    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        when (holder) {
            is DataItemViewHolder.BusinessCardViewHolder -> {
                val item = getItem(position) as DataItem.BusinessCardItem
                holder.bind(item.businessCard, cardListener)
            }
            is DataItemViewHolder.HeaderViewHolder -> {
                val item = getItem(position) as DataItem.Header
                holder.bind(item)
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.BusinessCardItem -> ITEM_VIEW_TYPE_ITEM
        }
    }


    fun addHeadersAndSubmitList(list: List<BusinessCard>?) {

        adapterScope.launch {
            val listDataItem = list?.toListOfDataItem()
            withContext(Dispatchers.Main) {
                submitList(listDataItem)
            }
        }

    }


    class DiffCallback : DiffUtil.ItemCallback<DataItem>() {

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem) = oldItem == newItem
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) = oldItem.id == newItem.id
    }
}