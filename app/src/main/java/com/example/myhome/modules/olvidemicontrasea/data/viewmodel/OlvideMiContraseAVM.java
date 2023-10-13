package com.example.myhome.modules.olvidemicontrasea.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.olvidemicontrasea.`data`.model.OlvideMiContraseAModel
import org.koin.core.KoinComponent

class OlvideMiContraseAVM : ViewModel(), KoinComponent {
  val olvideMiContraseAModel: MutableLiveData<OlvideMiContraseAModel> =
      MutableLiveData(OlvideMiContraseAModel())

  var navArguments: Bundle? = null
}
