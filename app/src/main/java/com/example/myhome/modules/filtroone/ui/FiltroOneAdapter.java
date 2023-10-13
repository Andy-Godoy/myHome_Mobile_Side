package com.example.myhome.modules.filtroone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowFiltroOneBinding
import com.example.myhome.modules.filtroone.`data`.model.FiltroOneRowModel
import kotlin.Int
import kotlin.collections.List

class FiltroOneAdapter(
  var list: List<FiltroOneRowModel>
) : RecyclerView.Adapter<FiltroOneAdapter.RowFiltroOneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFiltroOneVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_filtro_one,parent,false)
    return RowFiltroOneVH(view)
  }

  override fun onBindViewHolder(holder: RowFiltroOneVH, position: Int) {
    val filtroOneRowModel = FiltroOneRowModel()
    // TODO uncomment following line after integration with data source
    // val filtroOneRowModel = list[position]
    holder.binding.filtroOneRowModel = filtroOneRowModel
  }

  override fun getItemCount(): Int = 8
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<FiltroOneRowModel>) {
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
      item: FiltroOneRowModel
    ) {
    }
  }

  inner class RowFiltroOneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowFiltroOneBinding = RowFiltroOneBinding.bind(itemView)
  }
}
