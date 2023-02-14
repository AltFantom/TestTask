package com.example.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//общая вью модель для передачи данных
class SharedViewModel : ViewModel() {

    //переменная для передачи данных из фрагмента в активити
    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    //когда переменная изменится мы закроем 2й фрагмент
    private val _shouldCloseSecondFragment = MutableLiveData<Unit>()
    val shouldCloseSecondFragment: LiveData<Unit>
        get() = _shouldCloseSecondFragment

    //передаем данные в переменную
    fun sendData(data: String) {
        _data.value = data
    }

    //намеренно изменяем переменную, чтобы дать понять, что фрагмент нужно закрыть
    fun shouldCloseSecondFragment() {
        _shouldCloseSecondFragment.value = Unit
    }
}