package com.example.myhome.modules.propiedadseleccionadausuariostwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowListquinchoBinding
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ListquinchoRowModel
import kotlin.Int
import kotlin.collections.List

class ListquinchoAdapter(
  var list: List<ListquinchoRowModel>
) : RecyclerView.Adapter<ListquinchoAdapter.RowListquinchoVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListquinchoVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listquincho,parent,false)
    return RowListquinchoVH(view)
  }

  override fun onBindViewHolder(holder: RowListquinchoVH, position: Int) {
    val listquinchoRowModel = ListquinchoRowModel()
    // TODO uncomment following line after integration with data source
    // val listquinchoRowModel = list[position]
    holder.binding.listquinchoRowModel = listquinchoRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListquinchoRowModel>) {
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
      item: ListquinchoRowModel
    ) {
    }
  }

  inner class RowListquinchoVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListquinchoBinding = RowListquinchoBinding.bind(itemView)
  }
}
