package com.example.myhome.modules.logininmobiliariaerror.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class LoginInmobiliariaErrorModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEstsaccediend: String? =
      MyApp.getInstance().resources.getString(R.string.msg_est_s_accediend)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtErrordebeingr: String? =
      MyApp.getInstance().resources.getString(R.string.msg_error_debe_ingr)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContrasea: String? = MyApp.getInstance().resources.getString(R.string.lbl_contrase_a)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIngresalacont: String? =
      MyApp.getInstance().resources.getString(R.string.msg_ingresa_la_cont)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLvidemicontr: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lvide_mi_contr)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNotieneunac: String? = MyApp.getInstance().resources.getString(R.string.msg_no_tiene_una_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRegistreseaqu: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_registrese_aqu)
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
