package com.example.myhome.modules.nuevapropiedadpartethree.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class NuevaPropiedadParteThreeModel(
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
  var etDropdowntipopValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etHoveraltapropValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputprecioprValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputprecioprOneValue: String? = null
)
