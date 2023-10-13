package com.example.myhome.modules.nuevapropiedadparteone.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class NuevaPropiedadParteOneModel(
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
  var txtCalle: String? = MyApp.getInstance().resources.getString(R.string.lbl_calle)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNmero: String? = MyApp.getInstance().resources.getString(R.string.lbl_n_mero)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputnumero: String? = MyApp.getInstance().resources.getString(R.string.lbl_n_mero)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDto: String? = MyApp.getInstance().resources.getString(R.string.lbl_dto)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputdto: String? = MyApp.getInstance().resources.getString(R.string.lbl_dto)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPiso: String? = MyApp.getInstance().resources.getString(R.string.lbl_piso)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInputpiso: String? = MyApp.getInstance().resources.getString(R.string.lbl_piso)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPas: String? = MyApp.getInstance().resources.getString(R.string.lbl_pa_s)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProvincia: String? = MyApp.getInstance().resources.getString(R.string.lbl_provincia)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLocalidad: String? = MyApp.getInstance().resources.getString(R.string.lbl_localidad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBarrio: String? = MyApp.getInstance().resources.getString(R.string.lbl_barrio)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt1deFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_1_de_4)
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
  var etInputcalleValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputpaisValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputprovinciaValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputlocalidadValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputbarrioValue: String? = null
)
