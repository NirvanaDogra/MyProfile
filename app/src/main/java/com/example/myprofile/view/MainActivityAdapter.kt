package com.example.myprofile.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.view.BaseScreenModel
import com.example.commons.view.BaseViewHolder
import com.example.myprofile.viewmodel.GenericPostModel
import com.example.myprofile.viewmodel.ShowProfileViewModel

class MainActivityAdapter(var data: List<BaseScreenModel>, val viewModel: ShowProfileViewModel) :
    RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val GENERIC_VIEW_TYPE = 1
        private const val HOME_SCREEN_TYPE = 0
    }

    fun submitData(data: List<BaseScreenModel>) {
        this.data = data
        notifyItemRangeChanged(1, data.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return ViewSectionType.values()[viewType].getViewHolder(parent, viewModel)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setViewData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is GenericPostModel) {
            GENERIC_VIEW_TYPE
        } else {
            HOME_SCREEN_TYPE
        }
    }

}
