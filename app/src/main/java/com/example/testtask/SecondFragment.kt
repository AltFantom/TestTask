package com.example.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondBinding == null")

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    //данные, которые нам необходимо получить из агрументов
    private lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //получаем данные
        data = requireArguments().getString(DATA, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //присваиваем значение полученное из аргументов вью
        binding.tvData.text = data
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        //удаляем из backStack'a и возвращаемся к первому фрагменту
        binding.buttonBack.setOnClickListener {
            viewModel.shouldCloseSecondFragment()
        }
    }

    companion object {

        //ключ для передачи аргументов
        private const val DATA = "data"

        // Создаем фабричный метод и передаем через бандл нужные аргументы
        fun newInstance(data: String): SecondFragment {
            return SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(DATA, data)
                }
            }
        }
    }
}