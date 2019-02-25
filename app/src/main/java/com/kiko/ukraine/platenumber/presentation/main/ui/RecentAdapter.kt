package com.kiko.ukraine.platenumber.presentation.main.ui

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.ukraine.platenumber.databinding.ListRecentViewItemBinding
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo
import com.kiko.ukraine.platenumber.presentation.main.MainViewModel

class RecentAdapter(private val viewModel: MainViewModel) :
    PagedListAdapter<ShortPlateInfo, RecentViewHolder>(RecentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val viewDataBinding =
            ListRecentViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecentViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.binding.plate = getItem(position)
        holder.binding.viewModel = viewModel
    }
}

class RecentViewHolder(val binding: ListRecentViewItemBinding) : RecyclerView.ViewHolder(binding.root)

object RecentDiffCallback : DiffUtil.ItemCallback<ShortPlateInfo>() {

    override fun areItemsTheSame(i1: ShortPlateInfo, i2: ShortPlateInfo): Boolean {
        return i1.id == i2.id
    }

    override fun areContentsTheSame(i1: ShortPlateInfo, i2: ShortPlateInfo): Boolean {
        return i1.timestampOfCheck == i2.timestampOfCheck
    }

}