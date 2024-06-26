package com.example.otusmvvm.presentation.mvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.otusmvvm.databinding.ActivityMainBinding

class MainActivityMvp : AppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter: IMainPresenter by lazy {
        Test.presenter ?: error("error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Test.init(this)
        setContentView(binding.root)
        binding.plusButton.setOnClickListener {
            presenter.onPlusButtonClicked()
        }
        binding.minusButton.setOnClickListener {
            presenter.onMinusButtonClicked()
        }
    }

    override fun setWaterCount(text: String) {
        binding.counter.text = text
    }

    override fun showProgress() {
        Log.d("test ---->", "showProgress: ")
        binding.minusButton.isEnabled = false
        binding.plusButton.isEnabled = false
    }

    override fun hideProgress() {
        Log.d("test ---->", "hideProgress: ")
        binding.minusButton.isEnabled = true
        binding.plusButton.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        Test.detach()
    }
}