package com.example.androidnote

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnote.bean.MainRVBeana
import com.example.androidnote.file.MainFileActivity
import com.example.androidnote.media.MainMediaActivity
import com.example.androidnote.provider.MainProviderActivity
import com.example.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: ClassifyAdapter

    /**
     * 属性委托的标准语法是 var value by delegate,其中delegate是一个对象实列，意思就是把value的具体实现交给了delegate实例
     * lazy是一个高阶函数得到的就是一个delegate实例所以满足上面的语法(lazy函数就一行代码直接用等号连接从而获得delegate实例)
     * 而lazy中“函数”返回值使用了泛型 lambda的最后一行就是lambda的返回值所以lazy中的T就是StringBuilder类型了
     * 而在LaterClass的构造方法中也接受同样的函数类型参数 所以在laterClass中value = block()这句代码能够给value赋值，最后在使用  到num的时候会把value的值返回回来
     */
    private val num by lazy {
        val builder = StringBuilder()
        builder.append("hello kotlin by")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = mutableListOf<MainRVBeana>()
        list.add(MainRVBeana("CustomView", 0))
        list.add(MainRVBeana("Camera Album", 1))
        list.add(MainRVBeana("File Write", 2))
        list.add(MainRVBeana("File Read", 3))
        list.add(MainRVBeana("SQLite", 4))
        list.add(MainRVBeana("File", 5))
        list.add(MainRVBeana("ContentResolver", 0))
        mAdapter = ClassifyAdapter(list = list, context = this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(MyItemDecoration())
        recyclerView.adapter = mAdapter
        mAdapter.onClick = {
            Log.e("--lazy---", num.toString())
            when (it) {
                0 -> Toast.makeText(this, "CustomView", Toast.LENGTH_SHORT).show()
//                1 -> launcherActivity(this, MainMediaActivity::class.java)
                1 -> launchActivity<MainMediaActivity>(this)
                2 -> writeSomeThing()
                3 -> readHistory()
                5 -> launcherActivity(this, MainFileActivity::class.java)
                6 -> launcherActivity(this, MainProviderActivity::class.java)
            }
        }
        //rv利用匿名函数实现点击事件
//        mAdapter.onClick = fun(num:Int){
//            Toast.makeText(this, "匿名函数类型：position == $num", Toast.LENGTH_SHORT).show()
//        }


        //high-order简化SharedPreferences
        getSharedPreferences("MySharedPreferences", MODE_PRIVATE).saveData {
            putString("key", "hello world!!!")
        }

    }

    /**************************************************file读写相关******************************************************/
    private fun readHistory() {
        //读数据
        val builder = StringBuilder()
        val inputStream = openFileInput("myNotes")
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.use {
            it.forEachLine { builder.append(it) }
        }
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun writeSomeThing() {
        //写数据
        val outPutSystem = openFileOutput("myNotes", MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(outPutSystem))
        writer.use {
            it.write("hello world!!!")
        }
        Toast.makeText(this, "写出成功", Toast.LENGTH_SHORT).show()
    }

    /**
     * 自定义rv ItemDecoration
     */
    inner class MyItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = dp2px(10, this@MainActivity)
            outRect.left = dp2px(10, this@MainActivity)
            outRect.right = dp2px(10, this@MainActivity)
            val count = parent.adapter?.itemCount ?: 0
            outRect.bottom = if (parent.getChildAdapterPosition(view) == count - 1) dp2px(
                10,
                this@MainActivity
            ) else 0
        }
    }
}
