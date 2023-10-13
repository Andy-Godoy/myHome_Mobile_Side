package com.example.myhome.modules.perfilinmobiliariaone.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class PerfilInmobiliariaOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMiPerfil: String? = MyApp.getInstance().resources.getString(R.string.lbl_mi_perfil)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNombre: String? = MyApp.getInstance().resources.getString(R.string.lbl_nombre)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMoneda: String? = MyApp.getInstance().resources.getString(R.string.lbl_moneda)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputnombreValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPriceValue: String? = null
)
