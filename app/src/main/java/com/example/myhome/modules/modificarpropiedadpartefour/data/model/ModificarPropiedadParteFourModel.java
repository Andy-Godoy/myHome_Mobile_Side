package com.example.myhome.modules.modificarpropiedadpartefour.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class ModificarPropiedadParteFourModel(
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
  var txtFoto4png: String? = MyApp.getInstance().resources.getString(R.string.lbl_foto_4_png)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUploadedThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_uploaded)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtVideo1mpFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_video_1_mp4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUploadedFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_uploaded)
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
  var txtNuevaPropiedad: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_nueva_propiedad)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPerfil: String? = MyApp.getInstance().resources.getString(R.string.lbl_perfil2)

)
