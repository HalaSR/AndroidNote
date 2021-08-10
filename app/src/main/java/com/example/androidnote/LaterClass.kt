package com.example.androidnote

import kotlin.reflect.KProperty

/**
 * @Author lop
 * @Date 2021/8/10 20:45
 *
 * @Description
 */
class LaterClass<T>(val block: () -> T) {
    var value: Any? = null
    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}