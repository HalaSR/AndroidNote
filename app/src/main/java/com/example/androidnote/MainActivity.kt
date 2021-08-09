package com.example.androidnote

import android.content.SharedPreferences
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnote.bean.MainRVBeana
import com.example.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: ClassifyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = mutableListOf<MainRVBeana>()
        list.add(MainRVBeana("CustomView", 0))
        list.add(MainRVBeana("Object Animator", 1))
        mAdapter = ClassifyAdapter(list = list, context = this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(MyItemDecoration())
        recyclerView.adapter = mAdapter
        mAdapter.onClick = {
            //读数据
            val builder = StringBuilder()
            val inputStream = openFileInput("myNotes")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {
                it.forEachLine { builder.append(it) }
            }
            Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()
        }
//        mAdapter.onClick = fun(num:Int){
//            Toast.makeText(this, "匿名函数类型：position == $num", Toast.LENGTH_SHORT).show()
//        }


        //写数据
        val outPutSystem = openFileOutput("myNotes", MODE_APPEND)
        val writer = BufferedWriter(OutputStreamWriter(outPutSystem))
        writer.use {
            it.write("hello world!!!")
        }

        //high-order简化SharedPreferences
        getSharedPreferences("MySharedPreferences", MODE_PRIVATE).saveData {
            putString("key","hello world!!!")
        }

    }

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
