package com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ListlanguageOneRowModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ListquinchoRowModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.PropiedadSeleccionadaUsuariosTwoModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PropiedadSeleccionadaUsuariosTwoVM : ViewModel(), KoinComponent {
  val propiedadSeleccionadaUsuariosTwoModel: MutableLiveData<PropiedadSeleccionadaUsuariosTwoModel>
      = MutableLiveData(PropiedadSeleccionadaUsuariosTwoModel())

  var navArguments: Bundle? = null

  val listlanguageOneList: MutableLiveData<MutableList<ListlanguageOneRowModel>> =
      MutableLiveData(mutableListOf())

  val listquinchoList: MutableLiveData<MutableList<ListquinchoRowModel>> =
      MutableLiveData(mutableListOf())
}
