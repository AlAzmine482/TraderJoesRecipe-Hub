package com.example.traderjoes20

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traderjoes20.databinding.CellBinding
import com.squareup.picasso.Picasso

class RVAdapter(
    private val cell: ArrayList<Cell>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(var viewBinding: CellBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.viewBinding.itemIdTextview.text = cell[position].itemId
        itemViewHolder.viewBinding.itemNameTextview.text = cell[position].itemName
        itemViewHolder.viewBinding.itemPriceTextview.text = cell[position].itemPrice
        itemViewHolder.viewBinding.itemWeightTextview.text = cell[position].itemWeight

        Picasso.get().load(cell[position].itemUrl).into(itemViewHolder.viewBinding.imageView3)
    }

    override fun getItemCount(): Int {
        return cell.size
    }
}