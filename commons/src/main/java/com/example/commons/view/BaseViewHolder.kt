package com.example.commons.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setViewData(viewDataModel: BaseScreenModel)
}