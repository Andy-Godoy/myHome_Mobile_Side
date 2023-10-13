package com.example.myhome.modules.propiedadseleccionadausuarios.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowListlanguageThreeBinding
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ListlanguageThreeRowModel
import kotlin.Int
import kotlin.collections.List

class ListlanguageThreeAdapter(
  var list: List<ListlanguageThreeRowModel>
) : RecyclerView.Adapter<ListlanguageThreeAdapter.RowListlanguageThreeVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListlanguageThreeVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listlanguage_three,parent,false)
    return RowListlanguageThreeVH(view)
  }

  override fun onBindViewHolder(holder: RowListlanguageThreeVH, position: Int) {
    val listlanguageThreeRowModel = ListlanguageThreeRowModel()
    // TODO uncomment following line after integration with data source
    // val listlanguageThreeRowModel = list[position]
    holder.binding.listlanguageThreeRowModel = listlanguageThreeRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListlanguageThreeRowModel>) {
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
      item: ListlanguageThreeRowModel
    ) {
    }
  }

  inner class RowListlanguageThreeVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListlanguageThreeBinding = RowListlanguageThreeBinding.bind(itemView)
  }
}
