package com.example.myhome.modules.mensajeguardarpropiedadinformado.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class MensajeGuardarPropiedadInformadoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtReservarealiza: String? =
      MyApp.getInstance().resources.getString(R.string.msg_reserva_realiza)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAceptar: String? = MyApp.getInstance().resources.getString(R.string.lbl_aceptar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_la_reserva_se_h)

)
