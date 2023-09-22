package com.example.traderjoes20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PantryListAdapter(private val context: Context, private val items: List<String>) :
    ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        val item = getItem(position)
        if (item != null) {
            val textView = view!!.findViewById<TextView>(android.R.id.text1)
            textView.text = item
        }

        return view!!
    }
}
