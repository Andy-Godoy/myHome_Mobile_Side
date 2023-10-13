package com.example.myhome.modules.propiedadesfavoritosusuarioone.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class PropiedadesFavoritosUsuarioOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFavoritos: String? = MyApp.getInstance().resources.getString(R.string.lbl_favoritos2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFiltrar: String? = MyApp.getInstance().resources.getString(R.string.lbl_filtrar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUdCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_u_d_210_000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCharcasCounter: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_charcas_4000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPalermoSohoP: String? =
      MyApp.getInstance().resources.getString(R.string.msg_palermo_soho_p)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_95_m2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIMPECBalcnc: String? = MyApp.getInstance().resources.getString(R.string.msg_impec_balc_n_c)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDormitoriosCounter: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_3_dormitorios)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPropiedades: String? = MyApp.getInstance().resources.getString(R.string.lbl_propiedades)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_favoritos)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_perfil)

)
