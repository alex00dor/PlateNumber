package com.kiko.ukraine.platenumber.presentation.plateinfo.ui

import com.kiko.ukraine.platenumber.domain.entity.PlateInfo

data class UiModel(
    val status: Status = Status.NONE,
    val data: PlateInfo? = null,
    val error: String? = null
)

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    NONE
}