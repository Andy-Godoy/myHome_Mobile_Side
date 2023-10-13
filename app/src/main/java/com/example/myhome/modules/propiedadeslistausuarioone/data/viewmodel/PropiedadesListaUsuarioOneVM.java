package com.example.myhome.modules.propiedadeslistausuarioone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadeslistausuarioone.`data`.model.PropiedadesListaUsuarioOneModel
import org.koin.core.KoinComponent

class PropiedadesListaUsuarioOneVM : ViewModel(), KoinComponent {
  val propiedadesListaUsuarioOneModel: MutableLiveData<PropiedadesListaUsuarioOneModel> =
      MutableLiveData(PropiedadesListaUsuarioOneModel())

  var navArguments: Bundle? = null
}
