package com.example.otusmvvm.presentation.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusmvvm.domain.WaterCountInteractor

private const val WATER_STEP = 1

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = _state

    private val waterInteractor: WaterCountInteractor by lazy {
        WaterCountInteractor()
    }

    private var currentWater = waterInteractor.getStartData()

    init {
        _state.value = MainViewState(
            isProgress = false,
            currentWater = currentWater.toString()
        )
    }

    fun onPlusButtonClicked() {
        Thread {
            _state.postValue(
                MainViewState(isProgress = true)
            )
            Thread.sleep(500)
            currentWater = waterInteractor.plusWater(currentWater, WATER_STEP)
            val currentState = _state.value ?: MainViewState()
            _state.postValue(
                currentState.copy(
                    isProgress = false,
                    currentWater = "$currentWater"
                )
            )
        }.start()
    }

    fun onMinusButtonClicked() {
        Thread {
            _state.postValue(
                MainViewState(isProgress = true)
            )
            Thread.sleep(500)
            currentWater = waterInteractor.minusWater(currentWater, WATER_STEP)
            val currentState = _state.value ?: MainViewState()
            _state.postValue(
                currentState.copy(
                    isProgress = false,
                    currentWater = "$currentWater"
                )
            )
        }.start()
    }

    override fun onCleared() {
        Log.d("test ------>", "onCleared: ")
        super.onCleared()
    }
}