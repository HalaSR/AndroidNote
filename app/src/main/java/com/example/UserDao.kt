package com.example

import androidx.room.*
import com.example.androidnote.bean.User

/**
 * @Author lop
 * @Date 2021/9/22 22:20
 *
 * @Description 封装对数据库的操作
 */

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Delete
    fun deleteUser(user: User)

    /**
     * 以上三种操作只需要传入实体类并加上注解即可，当使用非实体类或者从数据库中查询数据就需要编写SQL语句
     * 使用非实体类参数去增删改查时只能使用Query注解
     */

    @Query("select * from User where age > :age")
    fun getUserByAge(age: Int): List<User>

    @Query("delete from User")
    fun clearAllUser()
}
