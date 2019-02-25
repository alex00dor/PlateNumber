package com.kiko.ukraine.platenumber.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.kiko.ukraine.platenumber.domain.entity.ShortPlateInfo
import com.kiko.ukraine.platenumber.domain.usecase.RecentViewsUseCase
import com.kiko.ukraine.platenumber.presentation.base.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val recentViewsUseCase: RecentViewsUseCase
) : ViewModel() {

    companion object {
        const val COUNT_OF_ITEM_ON_PAGE = 10
    }

    val navigateToPlate = SingleLiveEvent<String>()

    val recentViewsData: LiveData<PagedList<ShortPlateInfo>>
        get() = recentViewsUseCase.getRecentViewsPaged(COUNT_OF_ITEM_ON_PAGE)

    val plateNumberInputText = MutableLiveData<String>()

    fun navigateToInformation(plate : String){
        navigateToPlate.value = plate
    }
}