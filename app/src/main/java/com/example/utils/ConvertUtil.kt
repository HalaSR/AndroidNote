package com.example.utils

import android.content.Context
import android.util.TypedValue

/**
 * @Author lop
 * @Date 2021/8/8 11:45
 *
 * @Description dp转px sp转px
 */
fun dp2px(dpValue: Int, context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dpValue.toFloat(),
    context.resources.displayMetrics
).toInt()

fun sp2px(spValue: Int, context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    spValue.toFloat(),
    context.resources.displayMetrics
).toInt()