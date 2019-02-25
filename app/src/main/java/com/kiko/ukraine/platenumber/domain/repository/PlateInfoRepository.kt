package com.kiko.ukraine.platenumber.domain.repository

import com.kiko.ukraine.platenumber.domain.entity.PlateInfo
import io.reactivex.Single

interface PlateInfoRepository{
    fun getPlateInformation(plateNumber : String) : Single<PlateInfo>
}
