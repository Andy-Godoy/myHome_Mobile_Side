package com.example.myhome.modules.nuevapropiedadpartethree.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class GridcubiertosRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtCubiertos: String? = MyApp.getInstance().resources.getString(R.string.lbl_cubiertos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputmetroscuValue: String? = null
)
