package com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class PropiedadSeleccionadaUsuariosModel(
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
  var txtUsdCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_usd_220)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtExpensas: String? = MyApp.getInstance().resources.getString(R.string.lbl_expensas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsdCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_usd_50)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.msg_departamento)
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
  var txtQuincho: String? = MyApp.getInstance().resources.getString(R.string.lbl_quincho)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtJacuzzi: String? = MyApp.getInstance().resources.getString(R.string.lbl_jacuzzi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSUM: String? = MyApp.getInstance().resources.getString(R.string.lbl_sum)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLaundry: String? = MyApp.getInstance().resources.getString(R.string.lbl_laundry)
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
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_contactar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_reservar)

)
