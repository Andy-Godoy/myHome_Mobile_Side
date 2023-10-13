package com.example.myhome.modules.perfilinmobiliaria.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class PerfilInmobiliariaModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMiPerfil: String? = MyApp.getInstance().resources.getString(R.string.lbl_mi_perfil)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtForty: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_0)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFortyFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_5)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCalificacionesCounter: String? =
      MyApp.getInstance().resources.getString(R.string.msg_5_calificacione)
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
  var etInputnombreValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailOneValue: String? = null
)
