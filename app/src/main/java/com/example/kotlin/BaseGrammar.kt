package com.example.kotlin

import android.text.TextUtils
import kotlin.math.max

/**
 * @Author lop
 * @Date 2021/7/21 7:30
 *
 * @Description TODO 一些kotlin的基本语法
 */

fun main() {
    println("--------------------------------------------------if条件--------------------------------------------")
    println(String.format("最大数值为:%s", ifTest(19, 10)))

    println("--------------------------------------------------when条件--------------------------------------------")
    println(printGrade("John"))
    println(printGrade2("lopY"))

    println("--------------------------------------------------区间--------------------------------------------")
    val range = 0..2//0 until 10
    for (i in range){
        println("$i") 
    }
    for (i in 0 until 10 step 2){
        print(" $i")
    }
}

fun printGrade(name: String) = when (name) {
    "Tom" -> 10
    "John" -> 199
    "mike" -> 12
    else -> 0
}

fun printGrade2(name: String) = when {
    name.startsWith("J") -> 100
    name.contains("lop") -> 1001
    else -> 0
}

fun ifTest(num1: Int, num2: Int) = if (num1 > num2) num1 else num2
