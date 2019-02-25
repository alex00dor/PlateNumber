package com.kiko.ukraine.platenumber.domain.usecase

import com.kiko.ukraine.platenumber.domain.entity.PlateInfo
import com.kiko.ukraine.platenumber.domain.repository.PlateInfoRepository
import io.reactivex.Single

class PlateInfoUseCase(private val repository: PlateInfoRepository){

    fun getPlateInfo(plateNumber : String) : Single<PlateInfo> {
        return repository.getPlateInformation(plateNumber)
    }
}