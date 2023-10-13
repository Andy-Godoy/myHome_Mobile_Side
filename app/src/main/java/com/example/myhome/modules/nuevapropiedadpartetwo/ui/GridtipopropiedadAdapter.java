package com.example.myhome.modules.nuevapropiedadpartetwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.RowGridtipopropiedadBinding
import com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model.GridtipopropiedadRowModel
import kotlin.Int
import kotlin.collections.List

class GridtipopropiedadAdapter(
  var list: List<GridtipopropiedadRowModel>
) : RecyclerView.Adapter<GridtipopropiedadAdapter.RowGridtipopropiedadVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGridtipopropiedadVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_gridtipopropiedad,parent,false)
    return RowGridtipopropiedadVH(view)
  }

  override fun onBindViewHolder(holder: RowGridtipopropiedadVH, position: Int) {
    val gridtipopropiedadRowModel = GridtipopropiedadRowModel()
    // TODO uncomment following line after integration with data source
    // val gridtipopropiedadRowModel = list[position]
    holder.binding.gridtipopropiedadRowModel = gridtipopropiedadRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<GridtipopropiedadRowModel>) {
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
      item: GridtipopropiedadRowModel
    ) {
    }
  }

  inner class RowGridtipopropiedadVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGridtipopropiedadBinding = RowGridtipopropiedadBinding.bind(itemView)
  }
}
