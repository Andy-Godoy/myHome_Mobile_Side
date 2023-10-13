package com.example.myhome.modules.modificarpropiedadpartethree.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ModificarPropiedadParteThreeModel(
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
  var txtAntigedad: String? = MyApp.getInstance().resources.getString(R.string.lbl_antig_edad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAmenities: String? = MyApp.getInstance().resources.getString(R.string.lbl_amenities)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMetroscuadrado: String? =
      MyApp.getInstance().resources.getString(R.string.msg_metros_cuadrado)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCubiertos: String? = MyApp.getInstance().resources.getString(R.string.lbl_cubiertos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputmetroscu: String? = MyApp.getInstance().resources.getString(R.string.lbl_8)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSemicubiertos: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_semicubiertos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputmetrosse: String? = MyApp.getInstance().resources.getString(R.string.lbl_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescubiertos: String? = MyApp.getInstance().resources.getString(R.string.lbl_descubiertos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputmetrosde: String? = MyApp.getInstance().resources.getString(R.string.lbl_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPreciodePropi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_precio_de_propi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPreciodeexpen: String? =
      MyApp.getInstance().resources.getString(R.string.msg_precio_de_expen)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt3deFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_3_de_4)
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
  var etHoveraltapropValue: String? = null
)
