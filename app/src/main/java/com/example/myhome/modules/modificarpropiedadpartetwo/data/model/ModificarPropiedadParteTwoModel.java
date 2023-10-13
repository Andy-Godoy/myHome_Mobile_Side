package com.example.myhome.modules.modificarpropiedadpartetwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ModificarPropiedadParteTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEditarPropieda: String? =
      MyApp.getInstance().resources.getString(R.string.msg_editar_propieda)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTipoPropiedad: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_tipo_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEstado: String? = MyApp.getInstance().resources.getString(R.string.lbl_estado)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrientacin: String? = MyApp.getInstance().resources.getString(R.string.lbl_orientaci_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabel: String? = MyApp.getInstance().resources.getString(R.string.lbl_s)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPosicin: String? = MyApp.getInstance().resources.getString(R.string.lbl_posici_n)
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
  var txtInputcantidad: String? = MyApp.getInstance().resources.getString(R.string.lbl_3)
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
  var txtInputcantidadOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_2)
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
  var txtInputcantidadTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_1)
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
  var txtInputcantidadThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_2)
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
  var txtNuevaPropiedad: String? =
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
  var etDropdowntipopValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etHoverestadoinValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etDropdownposiciValue: String? = null
)
