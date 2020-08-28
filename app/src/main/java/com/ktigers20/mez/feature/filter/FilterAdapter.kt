package com.ktigers20.mez.feature.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ktigers20.mez.R
import kotlinx.android.synthetic.main.item_menu_filter.view.*

class FilterAdapter(
    private val filterList: ArrayList<String>,
    private val isClicked: (String) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title: String = filterList[position]
        holder.bind(title, isClicked)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu_filter, parent, false)
        ) {
        private val title: TextView = itemView.filter_name

        fun bind(filterName: String, isClicked: (String) -> Unit) {
            title.text = filterName
            itemView.setOnClickListener { isClicked(filterName) }
        }
    }
}