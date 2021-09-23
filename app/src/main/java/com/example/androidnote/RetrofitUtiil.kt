package com.example.androidnote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author lop
 * @Date 2021/9/1 22:31
 *
 * @Description TODO
 */
object RetrofitUtiil {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.baidu.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> createService():T = retrofit.create(T::class.java)
}