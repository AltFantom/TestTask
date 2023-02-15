package com.example.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//общая вью модель для передачи данных
class SharedViewModel : ViewModel() {

    private val _state = MutableLiveData<State>(State.FirstFragment)
    val state: LiveData<State>
        get() = _state

    //тут используется setValue
    fun startSecondFragment(data: String) {
        _state.value = State.SecondFragment(data)
    }

    // поствэлуе используем тогда, когда нам нужно изменить что-то в юай потоке, но из другого потока
    fun closeSecondFragment() {
        viewModelScope.launch {
            _state.postValue(State.ShouldCloseSecondFragment)
        }
    }
}