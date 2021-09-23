package com.example.androidnote.coroutines

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author lop
 * @Date 2021/9/5 15:11
 *
 * @Description 协程
 */
const val tag = "---------------->"
fun globalScopeTest(){
    Log.e("---------------->","main currentThread == ${Thread.currentThread().id}")
    //GlobalScope.launch会创建一个协程的作用域这样lambda中的代码就是运行在协程中了
    //这个方法创建的是一个顶层协程，它随着应用程序的结束而结束(即不会考虑lambda中的代码是否执行完毕)
    GlobalScope.launch {
        Log.e("---------------->","GlobalScope currentThread == ${Thread.currentThread().id}")
        launch {
            Log.e("---------------->","GlobalScope.launch currentThread == ${Thread.currentThread().id}")
        }
    }
}

fun runBlockingTest(){
    Log.e("---------------->","main currentThread == ${Thread.currentThread().id}")
    //这个方法也会创建一个协程的作用域 它会保证lambda中的代码一定会执行完成，在没有执行完成之前会一直阻塞主线程
    //在lambda中可以直接使用launch再创建子协程
    runBlocking {
        Log.e(tag,"runBlocking currentThread == ${Thread.currentThread().id}")
        delay(1000)
        Log.e(tag,"runBlocking finished")
    }
}