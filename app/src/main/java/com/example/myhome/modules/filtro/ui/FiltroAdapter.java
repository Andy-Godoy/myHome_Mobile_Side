package com.example.myhome.modules.filtro.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowFiltroBinding
import com.example.myhome.modules.filtro.`data`.model.FiltroRowModel
import kotlin.Int
import kotlin.collections.List

class FiltroAdapter(
  var list: List<FiltroRowModel>
) : RecyclerView.Adapter<FiltroAdapter.RowFiltroVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFiltroVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_filtro,parent,false)
    return RowFiltroVH(view)
  }

  override fun onBindViewHolder(holder: RowFiltroVH, position: Int) {
    val filtroRowModel = FiltroRowModel()
    // TODO uncomment following line after integration with data source
    // val filtroRowModel = list[position]
    holder.binding.filtroRowModel = filtroRowModel
  }

  override fun getItemCount(): Int = 8
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<FiltroRowModel>) {
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
      item: FiltroRowModel
    ) {
    }
  }

  inner class RowFiltroVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowFiltroBinding = RowFiltroBinding.bind(itemView)
  }
}
