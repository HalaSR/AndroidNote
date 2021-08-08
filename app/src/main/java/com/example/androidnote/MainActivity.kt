package com.example.androidnote

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
import com.example.utils.dp2px
import com.example.utils.example
import com.example.utils.minus
import com.example.utils.plus
import kotlinx.android.synthetic.main.activity_main.*
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

        Log.e("------->plus", example(20, 11, ::plus).toString())
        Log.e("------->minus", example(20, 11, ::minus).toString())
        Log.e("------->plus2", example(20, 11) { n1, n2 -> n1 + n2 }.toString())
        Log.e("------->minus2", example(20, 11) { n1, n2 -> n1 - n2 }.toString())
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
