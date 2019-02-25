package com.kiko.ukraine.platenumber.domain.usecase

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.kiko.ukraine.platenumber.domain.entity.PlateInfo
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo
import com.kiko.ukraine.platenumber.domain.repository.LocalRepository

class RecentViewsUseCase(private val repository: LocalRepository){

    fun getRecentViews() : LiveData<List<ShortPlateInfo>> = repository.getPlates()

    fun getRecentViewsPaged(countOnPage : Int) : LiveData<PagedList<ShortPlateInfo>> = repository.getPlatesPaged(countOnPage)

    fun addRecentView(plate : PlateInfo) = repository.add(ShortPlateInfo.fromPlateInfo(plate))

}