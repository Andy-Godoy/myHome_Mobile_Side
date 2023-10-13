package com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class NuevaPropiedadParteTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNuevaPropiedad: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_nueva_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTieneterraza: String? = MyApp.getInstance().resources.getString(R.string.lbl_tiene_terraza)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTienecochera: String? = MyApp.getInstance().resources.getString(R.string.lbl_tiene_cochera)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddeamb: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_amb)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddecoc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_coc)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTienebaulera: String? = MyApp.getInstance().resources.getString(R.string.lbl_tiene_baulera)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTienebalcn: String? = MyApp.getInstance().resources.getString(R.string.lbl_tiene_balc_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddeBa: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_ba2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddeCua: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_cua2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt2deFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_2_de_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPropiedades: String? = MyApp.getInstance().resources.getString(R.string.lbl_propiedades)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNuevaPropiedadOne: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_nueva_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPerfil: String? = MyApp.getInstance().resources.getString(R.string.lbl_perfil2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcantidadValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcantidadOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcantidadTwoValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcantidadThreeValue: String? = null
)
