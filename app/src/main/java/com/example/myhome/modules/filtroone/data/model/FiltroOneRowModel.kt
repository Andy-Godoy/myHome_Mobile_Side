package com.example.myhome.modules.filtroone.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class FiltroOneRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTipodepropied: String? =
      MyApp.getInstance().resources.getString(R.string.msg_tipo_de_propied)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etDropdowntipopValue: String? = null
)
