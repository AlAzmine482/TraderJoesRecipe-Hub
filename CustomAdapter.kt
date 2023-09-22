package com.example.traderjoes20
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class CustomAdapter (
    private var context: Context,
            private var tagsN: ArrayList<String>

) :
        RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        
        holder.tags.text = tagsN[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, tagsN[position], Toast.LENGTH_SHORT).show()
        }
    }


    override fun getItemCount(): Int {
            return tagsN.size
        }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView){
        var tags: TextView = itemView.findViewById<View>(R.id.tvTag) as TextView
    }
}

