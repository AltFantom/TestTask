package com.example.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModelStateObserver()
    }

    //запускаем первый фрагмент
    private fun startFirstFragment() {
        if (supportFragmentManager.findFragmentByTag(FIRST_FRAGMENT_NAME) == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    FirstFragment.newInstance(),
                    FIRST_FRAGMENT_NAME
                )
                .commit()
        }
    }

    private fun startSecondFragment() {
        if (supportFragmentManager.findFragmentByTag(SECOND_FRAGMENT_NAME) == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    SecondFragment.newInstance(),
                    SECOND_FRAGMENT_NAME
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupViewModelStateObserver() {
        /*переключаем фрагменты через обсервер, нужно так делать для того чтобы не вью сама решала
        что и когда нужно запускать, ибо в ней не должны быть логики, а уже просто реагировала
        на изменения и обрабатывала их так как нужно*/
        viewModel.state.observe(this) { screen ->
            when (screen) {
                is State.FirstFragment -> {
                    startFirstFragment()
                }
                is State.SecondFragment -> {
                    startSecondFragment()
                }
                is State.ShouldCloseSecondFragment -> {
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }

    companion object {
        private const val FIRST_FRAGMENT_NAME = "first"
        private const val SECOND_FRAGMENT_NAME = "second"
    }

}