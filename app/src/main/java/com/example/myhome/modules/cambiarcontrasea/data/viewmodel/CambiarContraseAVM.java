package com.example.myhome.modules.cambiarcontrasea.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.cambiarcontrasea.`data`.model.CambiarContraseAModel
import org.koin.core.KoinComponent

class CambiarContraseAVM : ViewModel(), KoinComponent {
  val cambiarContraseAModel: MutableLiveData<CambiarContraseAModel> =
      MutableLiveData(CambiarContraseAModel())

  var navArguments: Bundle? = null
}
