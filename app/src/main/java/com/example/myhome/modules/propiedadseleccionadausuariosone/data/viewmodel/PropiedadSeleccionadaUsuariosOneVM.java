package com.example.myhome.modules.propiedadseleccionadausuariosone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadseleccionadausuariosone.`data`.model.PropiedadSeleccionadaUsuariosOneModel
import org.koin.core.KoinComponent

class PropiedadSeleccionadaUsuariosOneVM : ViewModel(), KoinComponent {
  val propiedadSeleccionadaUsuariosOneModel: MutableLiveData<PropiedadSeleccionadaUsuariosOneModel>
      = MutableLiveData(PropiedadSeleccionadaUsuariosOneModel())

  var navArguments: Bundle? = null
}
