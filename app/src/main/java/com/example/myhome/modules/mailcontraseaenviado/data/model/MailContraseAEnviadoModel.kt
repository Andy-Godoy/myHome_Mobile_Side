package com.example.myhome.modules.mailcontraseaenviado.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class MailContraseAEnviadoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtElmailhasido: String? =
      MyApp.getInstance().resources.getString(R.string.msg_el_mail_ha_sido)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIntroduceelc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_introduce_el_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCdigo: String? = MyApp.getInstance().resources.getString(R.string.lbl_c_digo)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcodigoValue: String? = null
)
