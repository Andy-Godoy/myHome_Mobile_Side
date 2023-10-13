package com.example.myhome.modules.valoracionesycomentarios.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ValoracionesYComentariosModel(
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
  var txtValoracionesy: String? =
      MyApp.getInstance().resources.getString(R.string.msg_valoraciones_y)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLopezCastromil: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_lopez_castromil)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtForty: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_0)

)
