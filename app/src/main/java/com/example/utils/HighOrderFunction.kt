package com.example.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.example.androidnote.LaterClass
import java.lang.StringBuilder

/**
 * @Author lop
 * @Date 2021/8/8 17:06
 *
 * @Description 高阶函数
 */

fun example(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int) = num1 + num2
fun minus(num1: Int, num2: Int) = num1 - num2

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun SharedPreferences.saveData(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

fun <T> lazy(block: () -> T) = LaterClass(block)

fun <T> launcherActivity(context: Context, targetClass: Class<T>) {
    val intent = Intent(context, targetClass)
    context.startActivity(intent)
}