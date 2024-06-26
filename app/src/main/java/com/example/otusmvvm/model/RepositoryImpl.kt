package com.example.otusmvvm.model

import com.example.otusmvvm.domain.Repository

class RepositoryImpl() : Repository {
    override fun getData(): Int {
        return 10
    }
}


object DataSource {
    private var info: String = "Hello"
    fun getInfo() = info
    fun setData(newInfo: String) {
        info = newInfo
    }
}