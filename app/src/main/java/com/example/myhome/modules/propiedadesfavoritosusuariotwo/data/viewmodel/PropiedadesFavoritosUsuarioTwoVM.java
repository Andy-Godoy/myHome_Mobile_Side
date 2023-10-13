package com.example.myhome.modules.propiedadesfavoritosusuariotwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadesfavoritosusuariotwo.`data`.model.PropiedadesFavoritosUsuarioTwoModel
import org.koin.core.KoinComponent

class PropiedadesFavoritosUsuarioTwoVM : ViewModel(), KoinComponent {
  val propiedadesFavoritosUsuarioTwoModel: MutableLiveData<PropiedadesFavoritosUsuarioTwoModel> =
      MutableLiveData(PropiedadesFavoritosUsuarioTwoModel())

  var navArguments: Bundle? = null
}
