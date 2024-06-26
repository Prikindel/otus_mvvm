package com.example.otusmvvm.presentation.mvp

import com.example.otusmvvm.domain.WaterCountInteractor
import kotlin.math.atan

private const val WATER_STEP = 1

class MainPresenter(
    private var view: IMainView? = null
) : IMainPresenter {

    private val waterInteractor: WaterCountInteractor by lazy {
        WaterCountInteractor()
    }

    private var currentWater = waterInteractor.getStartData()

    init {
        view?.setWaterCount("$currentWater")
    }

    override fun attach(view: IMainView) {
        this.view = view
        view?.setWaterCount("$currentWater")
    }

    override fun dettach() {
        view = null
    }

    override fun onPlusButtonClicked() {
        view?.showProgress()
        currentWater = waterInteractor.plusWater(currentWater, WATER_STEP)
        view?.hideProgress()
        view?.setWaterCount("$currentWater")
    }

    override fun onMinusButtonClicked() {
        view?.showProgress()
        currentWater = waterInteractor.minusWater(currentWater, WATER_STEP)
        view?.hideProgress()
        view?.setWaterCount("$currentWater")
    }
}

object Test {
    var view: IMainView? = null
    var presenter: IMainPresenter? = null

    fun init(view: IMainView) {
        this.view = view
        if (presenter != null) {
            attach(view)
        } else {
            presenter = MainPresenter(view)
        }
    }

    fun attach(view: IMainView) {
        this.view = view
        presenter?.attach(view)
    }

    fun detach() {
        this.view = null
        presenter?.dettach()
    }
}