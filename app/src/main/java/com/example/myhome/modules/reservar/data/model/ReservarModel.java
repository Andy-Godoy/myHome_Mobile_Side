package com.example.myhome.modules.reservar.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ReservarModel(
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
  var txtReservar: String? = MyApp.getInstance().resources.getString(R.string.lbl_reservar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPropiedadares: String? =
      MyApp.getInstance().resources.getString(R.string.msg_propiedad_a_res)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCountry: String? = MyApp.getInstance().resources.getString(R.string.lbl_paraguay_4200)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBuenosAiresC: String? =
      MyApp.getInstance().resources.getString(R.string.msg_buenos_aires_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUdCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_u_d_220)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMontodereserv: String? =
      MyApp.getInstance().resources.getString(R.string.msg_monto_de_reserv)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_usd_110)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeleccionelm: String? =
      MyApp.getInstance().resources.getString(R.string.msg_seleccion_el_m)

)
