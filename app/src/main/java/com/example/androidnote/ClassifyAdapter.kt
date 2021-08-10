package com.example.androidnote

import android.content.Context
import android.content.IntentFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnote.bean.MainRVBeana

/**
 * @Author lop
 * @Date 2021/8/8 10:14
 *
 * @Description TODO
 */
class ClassifyAdapter(context: Context, list: List<MainRVBeana>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var list: List<MainRVBeana>

    init {
        this.context = context;
        this.list = list;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == MainRVBeana.TYPE_0)
            ClassifyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_rv_classify, parent, false)
            )
        else ClassifyViewHolder2(
            LayoutInflater.from(context).inflate(R.layout.item_rv_classify2, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = list.get(position).title
        when (holder) {
            is ClassifyViewHolder -> holder.textView.setText(msg)
            is ClassifyViewHolder2 -> holder.textView.setText(msg)
        }
        holder.itemView.setOnClickListener {
            //onClick指向一个函数类型的对象，可以怎样使用函数也就可以怎样使用它，除此之外函数类型的对象还有一个特有的方法invoke
            //对象是不能通过对象加括号来调用egobj() 但是函数类型的对象可以：onClick(position)它其实最终还是调用的onClick.invoke()
            //所以这里有两种方法实现点击事件  onCLick(position) == onClick.invoke()
//            onClick.invoke(position)
            onClick(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list.get(position).type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ClassifyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    inner class ClassifyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    lateinit var onClick: (Int) -> Unit
}