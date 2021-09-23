package com.example.androidnote.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Author lop
 * @Date 2021/9/6 22:34
 *
 * @Description 通过ViewModelProvider.Factory的onCreate去创建viewModel实例，而不是用ViewModelProviders.of(this).get(MainViewModel::class.java)这种方法创建实例
 * 1：只能通过构造方法创建实例才能向viewModel方法中传递参数
 * 2：factory中的create和activity的生命周期无关所以可以使用这种方式创建viewModel
 */
class MainViewModelFactory(val countReserved:Int) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}