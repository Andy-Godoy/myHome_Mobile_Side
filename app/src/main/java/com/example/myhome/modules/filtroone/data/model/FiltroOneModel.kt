package com.example.myhome.modules.filtroone.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class FiltroOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFiltros: String? = MyApp.getInstance().resources.getString(R.string.lbl_filtros)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddeba: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_ba)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCantidaddecua: String? =
      MyApp.getInstance().resources.getString(R.string.msg_cantidad_de_cua)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputcantidadOne: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_cant_de_ba_os)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrecio: String? = MyApp.getInstance().resources.getString(R.string.lbl_precio)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMin: String? = MyApp.getInstance().resources.getString(R.string.lbl_min)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMax: String? = MyApp.getInstance().resources.getString(R.string.lbl_max)
  ,
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
  var etLimpiarFiltrosValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputcantidadValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputprecioprValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputprecioprOneValue: String? = null
)
