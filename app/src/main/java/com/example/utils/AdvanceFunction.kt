package com.example.utils

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
