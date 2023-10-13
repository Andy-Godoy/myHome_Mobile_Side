package com.example.myhome.modules.resenas.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ResenasRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtAriel: String? = MyApp.getInstance().resources.getString(R.string.lbl_ariel)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFortyFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_5)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMuybuenapredi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_muy_buena_predi)

)
