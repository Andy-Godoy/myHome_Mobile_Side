package com.example.myhome.modules.modificarpropiedadpartefour.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ListticketRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFoto1png: String? = MyApp.getInstance().resources.getString(R.string.lbl_foto_1_png)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUploaded: String? = MyApp.getInstance().resources.getString(R.string.lbl_uploaded)

)
