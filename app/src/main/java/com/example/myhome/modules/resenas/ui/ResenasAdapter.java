package com.example.myhome.modules.resenas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowResenasBinding
import com.example.myhome.modules.resenas.`data`.model.ResenasRowModel
import kotlin.Int
import kotlin.collections.List

class ResenasAdapter(
  var list: List<ResenasRowModel>
) : RecyclerView.Adapter<ResenasAdapter.RowResenasVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowResenasVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_resenas,parent,false)
    return RowResenasVH(view)
  }

  override fun onBindViewHolder(holder: RowResenasVH, position: Int) {
    val resenasRowModel = ResenasRowModel()
    // TODO uncomment following line after integration with data source
    // val resenasRowModel = list[position]
    holder.binding.resenasRowModel = resenasRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ResenasRowModel>) {
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
      item: ResenasRowModel
    ) {
    }
  }

  inner class RowResenasVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowResenasBinding = RowResenasBinding.bind(itemView)
  }
}
