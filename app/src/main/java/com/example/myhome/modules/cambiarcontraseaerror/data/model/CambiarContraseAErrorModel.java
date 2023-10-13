package com.example.myhome.modules.cambiarcontraseaerror.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class CambiarContraseAErrorModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCambiarcontras: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cambiar_contras)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContrasea: String? = MyApp.getInstance().resources.getString(R.string.lbl_contrase_a)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_las_contrase_a)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmatucon: String? =
      MyApp.getInstance().resources.getString(R.string.msg_confirma_tu_con)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.msg_las_contrase_a)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputplaceholdValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputplaceholdOneValue: String? = null
)
