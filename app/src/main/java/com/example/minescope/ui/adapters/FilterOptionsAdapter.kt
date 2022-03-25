package com.example.minescope.ui.adapters

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R

class FilterOptionsAdapter(private var list: List<String>) : ListAdapter {
    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.relief_options_dialog,parent,false)

        return FilterOptionsViewHolder(view).itemView
    }

    override fun getItemViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }

    //VIEW HOLDER
    class FilterOptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //ATTRIBUTES

        //METHODS
        /**
         * This method is used to set up the data of each item of the options list.
         */
        fun bindData(option: String) {
        }
    }
}
