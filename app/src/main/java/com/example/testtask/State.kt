package com.example.testtask

//состояние для навигации между фрагментами
sealed class State {

    object FirstFragment : State()

    class SecondFragment(val data: String) : State()

    object ShouldCloseSecondFragment : State()

}
