package com.example.androidnote.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidnote.R
import kotlinx.android.synthetic.main.activity_main_coroutine.*
import kotlinx.coroutines.*

class MainCoroutineActivity : AppCompatActivity() {
    private val tag = "---------------->"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coroutine)


        //btnGlobalScope作用域
        btnGlobalScope.setOnClickListener {
            globalScopeTest()
        }

        //runBlocking协程作用域
        btnrunBlocking.setOnClickListener {
            runBlockingTest()
        }


        btnrunBlockinglaunch.setOnClickListener {
            Log.e(tag,"setOnClickListener currentThread == ${Thread.currentThread().id}")
            runBlocking {
                suspendTest()
                //runBlocking中的线程和主线程是同一线程
                //创建子协程，子协程的特点是外层作用域的协程结束后子协程也会结束
                //根据下面的打印可以看出两个launch中的代码是交替执行的，且都是再同一线程下，确实在单线程下模拟了多线程效果
                launch {
                    Log.e(tag,"launch1 currentThread == ${Thread.currentThread().id}")
                    delay(1000)
                    Log.e(tag,"launch1 协程结束")
                }
                launch {
                    Log.e(tag,"launch2 currentThread == ${Thread.currentThread().id}")
                    delay(1000)
                    Log.e(tag,"launch2 协程结束")
                }
            }
            Log.e(tag,"runBlocking all finished")
        }

        btnAsync.setOnClickListener {
            //从这里可以看出async创建的子协程返回的Deferred对象调用的await函数和coroutineScope函数是一样的都会阻塞当前协程
            //所以以下两个子协程是串行执行
            runBlocking {
                Log.e(tag,"开始执行协程")
                val startTime = System.currentTimeMillis()
                val result1 = async {
                    delay(1000)
                    5+5
                }.await()
                val reslut2 = async {
                    delay(1000)
                    5+5
                }.await()
                Log.e(tag,"all coroutine finished totalResult == ${result1+reslut2} cost time == ${System.currentTimeMillis()-startTime}")
            }
        }
        btnAsync2.setOnClickListener {
            Log.e(tag,"开始执行协程")
            val startTime = System.currentTimeMillis()
            //优化btnAsync中代码 并行执行
            runBlocking {
                val deferred1 = async {
                    delay(1000)
                    6+6
                }
                val deferred2 = async {
                    delay(1000)
                    6+6
                }
                Log.e(tag,"all coroutine finished totalResult == ${deferred1.await()+deferred2.await()} cost time == ${System.currentTimeMillis()-startTime}")

            }
        }
    }

    //挂起函数
    suspend fun suspendTest(){
        coroutineScope {
            launch {
                for (index in 1..10){
                    Log.e(tag,System.currentTimeMillis().toString())
                    delay(1000)
                }
            }
        }
    }
}