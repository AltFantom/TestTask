package com.example.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtonClickListener() {
        //при нажатии на Button считываем данные из EditText и отправляем их viewModel
        binding.buttonSendData.setOnClickListener {
            val data = binding.etData.text.toString()
            viewModel.startSecondFragment(data)
        }
    }

    companion object {
        //фабричный метод для создания объекта
        fun newInstance() = FirstFragment()
    }
}