package com.example.myhome.modules.cambiarcontraseaerror.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.cambiarcontraseaerror.`data`.model.CambiarContraseAErrorModel
import org.koin.core.KoinComponent

class CambiarContraseAErrorVM : ViewModel(), KoinComponent {
  val cambiarContraseAErrorModel: MutableLiveData<CambiarContraseAErrorModel> =
      MutableLiveData(CambiarContraseAErrorModel())

  var navArguments: Bundle? = null
}
