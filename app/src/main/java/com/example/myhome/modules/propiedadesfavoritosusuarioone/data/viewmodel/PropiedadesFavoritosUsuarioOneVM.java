package com.example.myhome.modules.propiedadesfavoritosusuarioone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadesfavoritosusuarioone.`data`.model.PropiedadesFavoritosUsuarioOneModel
import org.koin.core.KoinComponent

class PropiedadesFavoritosUsuarioOneVM : ViewModel(), KoinComponent {
  val propiedadesFavoritosUsuarioOneModel: MutableLiveData<PropiedadesFavoritosUsuarioOneModel> =
      MutableLiveData(PropiedadesFavoritosUsuarioOneModel())

  var navArguments: Bundle? = null
}
