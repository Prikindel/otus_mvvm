package com.example.otusmvvm.domain

import com.example.otusmvvm.model.DataSource
import com.example.otusmvvm.model.RepositoryImpl

class WaterCountInteractor(val repository: Repository = RepositoryImpl()) {

    fun getStartData(): Int {
        return repository.getData()
    }

    fun getInfo() = DataSource.getInfo()
    fun setInfo(info: String) = DataSource.setData(info)

    fun plusWater(current: Int, step: Int): Int {
        val result = current + step
        return result
    }

    fun minusWater(current: Int, step: Int): Int {
        val result = current - step
        return if (result < 0) {
            0
        } else {
            result
        }
    }
}