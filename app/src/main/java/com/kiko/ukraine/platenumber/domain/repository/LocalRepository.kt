package com.kiko.ukraine.platenumber.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo

interface LocalRepository{
    fun add(plate : ShortPlateInfo)
    fun getPlates() : LiveData<List<ShortPlateInfo>>
    fun getPlatesPaged(countItemOnPage: Int) : LiveData<PagedList<ShortPlateInfo>>
}