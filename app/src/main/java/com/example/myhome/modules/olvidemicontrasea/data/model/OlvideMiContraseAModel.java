package com.example.myhome.modules.olvidemicontrasea.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class OlvideMiContraseAModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOlvidastetuc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_olvidaste_tu_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIngresatumail: String? =
      MyApp.getInstance().resources.getString(R.string.msg_ingresa_tu_mail)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputmailValue: String? = null
)
