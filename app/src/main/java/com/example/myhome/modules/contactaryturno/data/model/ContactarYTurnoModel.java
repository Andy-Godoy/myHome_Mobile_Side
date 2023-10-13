package com.example.myhome.modules.contactaryturno.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ContactarYTurnoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPropiedades: String? = MyApp.getInstance().resources.getString(R.string.lbl_propiedades)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_favoritos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_perfil)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContactar: String? = MyApp.getInstance().resources.getString(R.string.lbl_contactar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContactalanu: String? =
      MyApp.getInstance().resources.getString(R.string.msg_contact_al_anu)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMartinFonseca: String? =
      MyApp.getInstance().resources.getString(R.string.msg_martin_fonseca)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFecha: String? = MyApp.getInstance().resources.getString(R.string.lbl_fecha)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDdmmyyyy: String? = MyApp.getInstance().resources.getString(R.string.lbl_dd_mm_yyyy)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTurno: String? = MyApp.getInstance().resources.getString(R.string.lbl_turno)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etNombreValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etDropdownconsulValue: String? = null
)
