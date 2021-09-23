package com.example.androidnote.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author lop
 * @Date 2021/9/23 21:58
 *
 * @Description TODO
 */
@Entity
data class Book(var name:String,var pages:Int){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}
