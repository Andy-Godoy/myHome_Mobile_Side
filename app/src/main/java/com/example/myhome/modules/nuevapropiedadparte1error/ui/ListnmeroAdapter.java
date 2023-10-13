package com.example.myhome.modules.nuevapropiedadparte1error.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowListnmeroBinding
import com.example.myhome.modules.nuevapropiedadparte1error.`data`.model.ListnmeroRowModel
import kotlin.Int
import kotlin.collections.List

class ListnmeroAdapter(
  var list: List<ListnmeroRowModel>
) : RecyclerView.Adapter<ListnmeroAdapter.RowListnmeroVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListnmeroVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listnmero,parent,false)
    return RowListnmeroVH(view)
  }

  override fun onBindViewHolder(holder: RowListnmeroVH, position: Int) {
    val listnmeroRowModel = ListnmeroRowModel()
    // TODO uncomment following line after integration with data source
    // val listnmeroRowModel = list[position]
    holder.binding.listnmeroRowModel = listnmeroRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListnmeroRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: ListnmeroRowModel
    ) {
    }
  }

  inner class RowListnmeroVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListnmeroBinding = RowListnmeroBinding.bind(itemView)
  }
}
