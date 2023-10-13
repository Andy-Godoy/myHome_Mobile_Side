package com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ListlanguageOneRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne1: String? = MyApp.getInstance().resources.getString(R.string.lbl_ambientes_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne2: String? = MyApp.getInstance().resources.getString(R.string.lbl_ba_os_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_cocheras_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_habitaciones_2)

)
