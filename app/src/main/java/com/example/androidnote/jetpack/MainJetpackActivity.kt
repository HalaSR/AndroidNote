package com.example.androidnote.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.AppDatabase
import com.example.androidnote.R
import com.example.androidnote.bean.Book
import com.example.androidnote.bean.User
import kotlinx.android.synthetic.main.activity_main_jetpack.*
import kotlin.concurrent.thread

class MainJetpackActivity : AppCompatActivity() {
    private var viewMode: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_jetpack)
        lifecycle.addObserver(MyLifecycleObserver(lifecycle))
        //不能通过自己直接去调用viewModel构造方法去创建实例，viewModel的生命周期长于activity这样就相当于和activity的生命周期绑定起来了每次走onCreate都会去创建一个实例(如横竖屏切换)
//        viewMode = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewMode =
            ViewModelProviders.of(this, MainViewModelFactory(1)).get(MainViewModel::class.java)
        btnViewModel.setOnClickListener(fun(view: View): Unit {
            Log.e("------------------>", viewMode!!.count++.toString())
        })

        btnPlus.setOnClickListener {
            viewMode?.plus()
        }
        btnClear.setOnClickListener {
            viewMode?.clear()
        }
        btnPlusPost.setOnClickListener {
            viewMode?.minus()
        }
        viewMode?.counter2?.value
        viewMode?.counter?.observe(this, Observer {
            Log.e("----------->", "liveData observe value : $it")
        })
        viewMode?.counter?.observe(this, fun(params: Int) {

        })


        val userDao = AppDatabase.getDatabase(this).fetchUserDao()
        val user1 = User()
        user1.firstName = "lop"
        user1.lastName = "coder"
        user1.age = 24

        val user2 = User()
        user2.firstName = "ly"
        user2.lastName = "man"
        user2.age = 24
        btnRoomInsert.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
                Log.e("------------->", "user1.id == ${user1.id}  user2.id == ${user2.id}")
            }
        }
        btnRoomUpdate.setOnClickListener {
            thread {
                user1.age = 25
                userDao.updateUser(user1)
            }
        }
        btnRoomQuery.setOnClickListener {
            thread {
                val list = userDao.getUserByAge(22)
                for (user in list) {
                    Log.e("------------->", "user.name == ${user.firstName} age == ${user.age}")
                }
            }
        }

        btnRoomClear.setOnClickListener {
            thread {
                userDao.clearAllUser()
            }
        }

        val book1 = Book("男孩、鼹鼠、狐狸和马",100)
        val book2 = Book("瓦尔登湖",123)
        val bookDao = AppDatabase.getDatabase(this).fetchBookDao()
        btnRoomInsert2.setOnClickListener {
            thread {
                Log.e("------------->2","book1 id == ${bookDao.insertBook(book1)}")
                Log.e("------------->2","book2 id == ${bookDao.insertBook(book2)}")
            }
        }
        btnRoomQuery2.setOnClickListener {
            thread {
                for (item in bookDao.queryAllBook()){
                    Log.e("------------->2","${item.name}  ${item.pages}")
                }
            }
        }

        btnRoomClear2.setOnClickListener {
            thread {
                bookDao.clearAllBook()
            }
        }
    }
}