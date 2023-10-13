package com.example.myhome.modules.nuevapropiedadpartethree.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowGridcubiertosBinding
import com.example.myhome.modules.nuevapropiedadpartethree.`data`.model.GridcubiertosRowModel
import kotlin.Int
import kotlin.collections.List

class GridcubiertosAdapter(
  var list: List<GridcubiertosRowModel>
) : RecyclerView.Adapter<GridcubiertosAdapter.RowGridcubiertosVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGridcubiertosVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gridcubiertos,parent,false)
    return RowGridcubiertosVH(view)
  }

  override fun onBindViewHolder(holder: RowGridcubiertosVH, position: Int) {
    val gridcubiertosRowModel = GridcubiertosRowModel()
    // TODO uncomment following line after integration with data source
    // val gridcubiertosRowModel = list[position]
    holder.binding.gridcubiertosRowModel = gridcubiertosRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<GridcubiertosRowModel>) {
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
      item: GridcubiertosRowModel
    ) {
    }
  }

  inner class RowGridcubiertosVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGridcubiertosBinding = RowGridcubiertosBinding.bind(itemView)
  }
}
