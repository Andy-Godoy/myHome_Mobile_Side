package com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class PropiedadSeleccionadaUsuariosTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPropiedad: String? = MyApp.getInstance().resources.getString(R.string.lbl_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEnalquiler: String? = MyApp.getInstance().resources.getString(R.string.lbl_en_alquiler)
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
  var txtPrecio: String? = MyApp.getInstance().resources.getString(R.string.lbl_precio2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsdCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_usd_220_000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtExpensas: String? = MyApp.getInstance().resources.getString(R.string.lbl_expensas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsdCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_usd_18_590)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_departamento)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCaractersticas: String? =
      MyApp.getInstance().resources.getString(R.string.msg_caracter_sticas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescripcin: String? = MyApp.getInstance().resources.getString(R.string.lbl_descripci_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_departamento_de)
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

)
