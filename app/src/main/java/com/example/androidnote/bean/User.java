package com.example.androidnote.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author lop
 * @Date 2021/9/22 22:15
 * @Description Entity注解声明了这是一个room的实体类，PrimaryKey注解声明了id作为主键，autoGenerate = true表示主键的值自动生成
 */
@Entity
public class User {
    private String firstName;
    private String lastName;
    private int age;

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
