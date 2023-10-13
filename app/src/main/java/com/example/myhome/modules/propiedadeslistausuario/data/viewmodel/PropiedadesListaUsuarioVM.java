package com.example.myhome.modules.propiedadeslistausuario.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadeslistausuario.`data`.model.PropiedadesListaUsuarioModel
import org.koin.core.KoinComponent

class PropiedadesListaUsuarioVM : ViewModel(), KoinComponent {
  val propiedadesListaUsuarioModel: MutableLiveData<PropiedadesListaUsuarioModel> =
      MutableLiveData(PropiedadesListaUsuarioModel())

  var navArguments: Bundle? = null
}
