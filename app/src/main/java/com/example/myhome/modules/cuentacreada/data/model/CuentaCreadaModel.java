package com.example.myhome.modules.cuentacreada.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class CuentaCreadaModel(
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
  var txtSucuentahasi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_su_cuenta_ha_si)

)
