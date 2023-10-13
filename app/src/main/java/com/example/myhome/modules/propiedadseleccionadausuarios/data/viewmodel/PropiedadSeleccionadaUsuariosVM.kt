package com.example.myhome.modules.propiedadseleccionadausuarios.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ListlanguageThreeRowModel
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.PropiedadSeleccionadaUsuariosModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PropiedadSeleccionadaUsuariosVM : ViewModel(), KoinComponent {
  val propiedadSeleccionadaUsuariosModel: MutableLiveData<PropiedadSeleccionadaUsuariosModel> =
      MutableLiveData(PropiedadSeleccionadaUsuariosModel())

  var navArguments: Bundle? = null

  val listlanguageThreeList: MutableLiveData<MutableList<ListlanguageThreeRowModel>> =
      MutableLiveData(mutableListOf())
}
