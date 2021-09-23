package com.example.androidnote.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author lop
 * @Date 2021/9/6 22:05
 *
 * @Description
 */
class MainViewModel(countReserved: Int) : ViewModel() {
    var count: Int = countReserved

    private val _counter = MutableLiveData<Int>()
    val counter2:LiveData<Int>get() = _counter

    val counter = MutableLiveData<Int>()

    fun plus() {
        counter.value = (counter.value ?: 0) + 1
    }

    fun clear() {
        counter.value = 0
    }

    fun minus() {
        counter.postValue((counter.value ?: 0) - 1)
    }
}