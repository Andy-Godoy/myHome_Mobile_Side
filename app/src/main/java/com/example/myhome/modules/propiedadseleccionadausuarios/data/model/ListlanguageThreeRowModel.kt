package com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ListlanguageThreeRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree1: String? = MyApp.getInstance().resources.getString(R.string.lbl_ambientes_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_ba_os_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_cocheras_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree2: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_habitaciones_2)

)
