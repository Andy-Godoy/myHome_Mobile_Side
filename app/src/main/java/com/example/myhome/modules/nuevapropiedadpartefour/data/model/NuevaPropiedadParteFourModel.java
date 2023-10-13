package com.example.myhome.modules.nuevapropiedadpartefour.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class NuevaPropiedadParteFourModel(
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
  var txtDescripcin: String? = MyApp.getInstance().resources.getString(R.string.lbl_descripci_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_descripci_n2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFotosVideos: String? = MyApp.getInstance().resources.getString(R.string.lbl_fotos_videos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeleccionararc: String? =
      MyApp.getInstance().resources.getString(R.string.msg_seleccionar_arc)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_guardar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt4deFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_de_4)
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

)
