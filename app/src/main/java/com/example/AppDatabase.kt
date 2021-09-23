package com.example

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidnote.bean.Book
import com.example.androidnote.bean.User

/**
 * @Author lop
 * @Date 2021/9/22 23:43
 *
 * @Description 1：版本号    2：包含哪些实体类可以用，隔开
 */
@Database(version = 2,entities = [User::class,Book::class])
abstract class AppDatabase :RoomDatabase(){
    abstract fun fetchUserDao(): UserDao//这里提供了Dao层的访问实例,这里只需要提供abstract方法具体实现逻辑Room的底层会去实现
    abstract fun fetchBookDao():BookDao
    companion object{
        val migration_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null,name text not null,pages integer not null)")
            }

        }
        var instance:AppDatabase? = null
        @Synchronized
        fun getDatabase(context: Context):AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database")
                .addMigrations(migration_1_2)
                .build().apply {
                instance = this
            }
        }
    }
}