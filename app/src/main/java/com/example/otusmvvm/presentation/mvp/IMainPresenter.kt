package com.example.otusmvvm.presentation.mvp

interface IMainPresenter {
    fun onPlusButtonClicked()
    fun onMinusButtonClicked()
    fun attach(view: IMainView)
    fun dettach()
}