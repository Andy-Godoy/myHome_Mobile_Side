package com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class GridtipopropiedadRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTipoPropiedad: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_tipo_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etDropdowntipopValue: String? = null
)
