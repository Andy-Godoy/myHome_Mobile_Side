package com.example.myhome.modules.modificarpropiedadpartefour.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowListticketBinding
import com.example.myhome.modules.modificarpropiedadpartefour.`data`.model.ListticketRowModel
import kotlin.Int
import kotlin.collections.List

class ListticketAdapter(
  var list: List<ListticketRowModel>
) : RecyclerView.Adapter<ListticketAdapter.RowListticketVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListticketVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listticket,parent,false)
    return RowListticketVH(view)
  }

  override fun onBindViewHolder(holder: RowListticketVH, position: Int) {
    val listticketRowModel = ListticketRowModel()
    // TODO uncomment following line after integration with data source
    // val listticketRowModel = list[position]
    holder.binding.listticketRowModel = listticketRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListticketRowModel>) {
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
      item: ListticketRowModel
    ) {
    }
  }

  inner class RowListticketVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListticketBinding = RowListticketBinding.bind(itemView)
  }
}
