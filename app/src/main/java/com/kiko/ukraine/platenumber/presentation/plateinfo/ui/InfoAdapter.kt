package com.kiko.ukraine.platenumber.presentation.plateinfo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.ukraine.platenumber.databinding.InfoElementBinding
import com.kiko.ukraine.platenumber.presentation.plateinfo.PlateInfoViewModel
import com.kiko.ukraine.platenumber.presentation.plateinfo.entity.Info

class InfoAdapter(
    val viewModel: PlateInfoViewModel,
    private val infoList: List<Info>
) : RecyclerView.Adapter<InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = InfoElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) = holder.bind(viewModel, infoList[position])

}


class InfoViewHolder(val binding: InfoElementBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: PlateInfoViewModel, info: Info) {
        binding.viewModel = viewModel
        binding.info = info
    }
}