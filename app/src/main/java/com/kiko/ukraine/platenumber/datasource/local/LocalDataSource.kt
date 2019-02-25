package com.kiko.ukraine.platenumber.datasource.local

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kiko.ukraine.platenumber.datasource.local.model.PlateInfoDao
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo
import com.kiko.ukraine.platenumber.domain.repository.LocalRepository
import kotlinx.coroutines.*

class LocalDataSource(private val plateInfoDao: PlateInfoDao) : LocalRepository {

    override fun getPlatesPaged(countItemOnPage: Int): LiveData<PagedList<ShortPlateInfo>> {
        return LivePagedListBuilder<Int, ShortPlateInfo>(
            plateInfoDao.getPlatesPaged(),
            countItemOnPage
        ).build()
    }

    override fun add(plate: ShortPlateInfo) {
        GlobalScope.launch { plateInfoDao.add(plate) }
    }


    override fun getPlates(): LiveData<List<ShortPlateInfo>> = plateInfoDao.getPlates()

}