package com.kiko.ukraine.platenumber.datasource.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kiko.ukraine.platenumber.datasource.local.model.PlateInfoDao
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo

@Database(entities = [ShortPlateInfo::class], version = 1, exportSchema = true)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun plateInfoDao() : PlateInfoDao
}