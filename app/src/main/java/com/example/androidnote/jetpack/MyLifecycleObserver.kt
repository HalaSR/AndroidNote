package com.example.androidnote.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author lop
 * @Date 2021/9/6 23:00
 *
 * @Description activity生命周期的监听者
 */
class MyLifecycleObserver(val lifeCycle:Lifecycle) :LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityStart(){
        Log.e("-------->","onCreate from activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityResume(){
        Log.e("-------->","onResume from activityResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun activityAny(){
        Log.e("-------->","activityAny currentLifeCycle state == ${lifeCycle.currentState}")
    }
}