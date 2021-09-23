package com.example

import androidx.room.*
import com.example.androidnote.bean.Book

/**
 * @Author lop
 * @Date 2021/9/23 22:06
 *
 * @Description 封装对数据库的操作
 */
@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Delete
    fun deleteBook(book: Book)

    @Update
    fun upDataBookByBook(book: Book)

    //发现的一个问题当用Query注解的时候里面如果还是以select开头则该方法必须有返回值否则报错，而以delete开头则不用
    @Query("select * from Book where name = :bookName")
    fun upDataBookByBookName(bookName: String):Book

    @Query("select * from Book")
    fun queryAllBook(): List<Book>

    @Query("delete from Book")
    fun clearAllBook()
}