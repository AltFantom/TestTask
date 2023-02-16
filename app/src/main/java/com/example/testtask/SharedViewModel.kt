package com.example.testtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//общая вью модель для передачи данных
class SharedViewModel : ViewModel() {

    val state = MutableLiveData<State>(State.FirstFragment)

}