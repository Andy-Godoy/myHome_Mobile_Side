package com.example.myhome.modules.valoracionesycomentarios.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowListellipseelevenBinding
import com.example.myhome.modules.valoracionesycomentarios.`data`.model.ListellipseelevenRowModel
import kotlin.Int
import kotlin.collections.List

class ListellipseelevenAdapter(
  var list: List<ListellipseelevenRowModel>
) : RecyclerView.Adapter<ListellipseelevenAdapter.RowListellipseelevenVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListellipseelevenVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listellipseeleven,parent,false)
    return RowListellipseelevenVH(view)
  }

  override fun onBindViewHolder(holder: RowListellipseelevenVH, position: Int) {
    val listellipseelevenRowModel = ListellipseelevenRowModel()
    // TODO uncomment following line after integration with data source
    // val listellipseelevenRowModel = list[position]
    holder.binding.listellipseelevenRowModel = listellipseelevenRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListellipseelevenRowModel>) {
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
      item: ListellipseelevenRowModel
    ) {
    }
  }

  inner class RowListellipseelevenVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListellipseelevenBinding = RowListellipseelevenBinding.bind(itemView)
  }
}
