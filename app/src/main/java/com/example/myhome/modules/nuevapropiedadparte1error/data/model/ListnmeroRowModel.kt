package com.example.myhome.modules.nuevapropiedadparte1error.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ListnmeroRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtNmero: String? = MyApp.getInstance().resources.getString(R.string.lbl_n_mero)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputplaceholdOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_n_mero)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtErrordebeingrOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_error_debe_ingr3)

)
