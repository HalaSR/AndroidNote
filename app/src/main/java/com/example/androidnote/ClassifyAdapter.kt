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
            onClick.invoke(position)
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

    private lateinit var onClick: (Int) -> Unit
    fun setOnItemClickListener(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }
}