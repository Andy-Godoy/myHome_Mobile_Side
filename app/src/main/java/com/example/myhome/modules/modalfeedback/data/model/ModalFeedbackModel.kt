package com.example.myhome.modules.modalfeedback.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ModalFeedbackModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_muchas_gracias)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtQuetepareci: String? = MyApp.getInstance().resources.getString(R.string.msg_que_te_pareci)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTegustarade: String? = MyApp.getInstance().resources.getString(R.string.msg_te_gustar_a_de)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMiexperiencia: String? =
      MyApp.getInstance().resources.getString(R.string.msg_mi_experiencia)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAceptar: String? = MyApp.getInstance().resources.getString(R.string.lbl_aceptar)

)
