package com.kiko.ukraine.platenumber.presentation.plateinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kiko.ukraine.platenumber.domain.usecase.PlateInfoUseCase
import com.kiko.ukraine.platenumber.domain.usecase.RecentViewsUseCase
import com.kiko.ukraine.platenumber.presentation.base.SingleLiveEvent
import com.kiko.ukraine.platenumber.presentation.plateinfo.ui.Status
import com.kiko.ukraine.platenumber.presentation.plateinfo.ui.UiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PlateInfoViewModel @Inject constructor(
    private val plateInfoUseCase: PlateInfoUseCase,
    private val recentViewsUseCase: RecentViewsUseCase
) : ViewModel() {

    val disposables = CompositeDisposable()
    var snackbarMessage = SingleLiveEvent<Int>()
    val copyBuffer = SingleLiveEvent<String>()

    val uiModel: LiveData<UiModel>
        get() = _uiModel

    private var _uiModel = MutableLiveData<UiModel>()

    private var plateNumber = String()


    fun setPlateNumber(number: String) {
        if(number != plateNumber) {
            plateNumber = number
            loadPlateInfo(number)
        }else if(uiModel.value != null && uiModel.value!!.status != Status.SUCCESS){
            loadPlateInfo(number)
        }
    }


    private fun loadPlateInfo(number: String) {
        disposables.add(plateInfoUseCase
            .getPlateInfo(number)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _uiModel.value = UiModel(Status.LOADING)
            }
            .delay(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _uiModel.value = UiModel(Status.SUCCESS, result)
                    recentViewsUseCase.addRecentView(result)
                },
                { t ->
                    _uiModel.value = UiModel(Status.ERROR, error = t.localizedMessage)
                }
            )
        )
    }

    fun retry(){
        setPlateNumber(plateNumber)
    }


    fun copyToBuffer(value: String): Boolean {
        copyBuffer.value = value
        return true
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
