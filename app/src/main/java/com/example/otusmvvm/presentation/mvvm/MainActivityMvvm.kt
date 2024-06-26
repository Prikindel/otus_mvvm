package com.example.otusmvvm.presentation.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.otusmvvm.databinding.ActivityMainBinding
import com.example.otusmvvm.presentation.mvp.IMainPresenter
import com.example.otusmvvm.presentation.mvp.IMainView
import com.example.otusmvvm.presentation.mvp.MainPresenter

class MainActivityMvvm : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.plusButton.setOnClickListener {
            viewModel.onPlusButtonClicked()
        }
        binding.minusButton.setOnClickListener {
            viewModel.onMinusButtonClicked()
        }

        viewModel.viewState.observe(this) { state ->
            if (state.isProgress) {
                showProgress()
            } else {
                setWaterCount(state.currentWater)
                hideProgress()
            }
        }
    }

    private fun setWaterCount(text: String) {
        binding.counter.text = text
    }

    private fun showProgress() {
        Log.d("test ---->", "MVVM showProgress: ")
        binding.minusButton.isEnabled = false
        binding.plusButton.isEnabled = false
    }

    private fun hideProgress() {
        Log.d("test ---->", "MVVM hideProgress: ")
        binding.minusButton.isEnabled = true
        binding.plusButton.isEnabled = true
    }
}