package com.example.myhome.modules.avisocompartir.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.avisocompartir.`data`.model.AvisoCompartirModel
import org.koin.core.KoinComponent

class AvisoCompartirVM : ViewModel(), KoinComponent {
  val avisoCompartirModel: MutableLiveData<AvisoCompartirModel> =
      MutableLiveData(AvisoCompartirModel())

  var navArguments: Bundle? = null
}
