package com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ListquinchoRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtQuincho: String? = MyApp.getInstance().resources.getString(R.string.lbl_quincho)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtJacuzzi: String? = MyApp.getInstance().resources.getString(R.string.lbl_jacuzzi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSUM: String? = MyApp.getInstance().resources.getString(R.string.lbl_sum)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLaundry: String? = MyApp.getInstance().resources.getString(R.string.lbl_laundry)

)
