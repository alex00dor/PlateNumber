package com.kiko.ukraine.platenumber.datasource.local.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.paging.DataSource
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo
@Dao
interface PlateInfoDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(plate : ShortPlateInfo)

    @Query("SELECT * FROM ShortPlateInfo order by timestamp DESC")
    fun getPlates() : LiveData<List<ShortPlateInfo>>

    @Query("SELECT * FROM ShortPlateInfo order by timestamp DESC")
    fun getPlatesPaged() : DataSource.Factory<Int, ShortPlateInfo>
}