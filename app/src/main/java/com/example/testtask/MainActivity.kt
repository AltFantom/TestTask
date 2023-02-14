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
        //чтобы после пересоздания активити не открывала 1й фрагмент
        if (savedInstanceState == null) {
            startFirstFragment()
        }
        setupViewModelObservers {data ->
            /*открываем 2й фрагмент и кладем его в backStack для того чтобы потом его можно было от
            туда убрать и вернуться на 1й фрагмент*/
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SecondFragment.newInstance(data))
                .addToBackStack(null)
                .commit()
        }
    }

    //запускаем первый фрагмент
    private fun startFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FirstFragment.newInstance())
            .commit()
    }

    //требуемая функция высшего порядка
    private inline fun setupViewModelObservers(crossinline openSecondFragment: (data: String) -> Unit) {
        viewModel.data.observe(this) { data ->
            openSecondFragment(data)
        }

        //убираем 2й фрагмент из стека и показываем пользователю 1й фрагмент
        viewModel.shouldCloseSecondFragment.observe(this) {
            supportFragmentManager.popBackStack()
        }
    }

}