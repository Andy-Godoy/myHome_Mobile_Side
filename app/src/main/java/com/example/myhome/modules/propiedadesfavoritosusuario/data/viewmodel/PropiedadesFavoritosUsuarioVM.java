package com.example.myhome.modules.propiedadesfavoritosusuario.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadesfavoritosusuario.`data`.model.PropiedadesFavoritosUsuarioModel
import org.koin.core.KoinComponent

class PropiedadesFavoritosUsuarioVM : ViewModel(), KoinComponent {
  val propiedadesFavoritosUsuarioModel: MutableLiveData<PropiedadesFavoritosUsuarioModel> =
      MutableLiveData(PropiedadesFavoritosUsuarioModel())

  var navArguments: Bundle? = null
}
