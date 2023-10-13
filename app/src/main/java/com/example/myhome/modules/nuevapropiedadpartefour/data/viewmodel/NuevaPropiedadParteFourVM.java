package com.example.myhome.modules.nuevapropiedadpartefour.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.nuevapropiedadpartefour.`data`.model.NuevaPropiedadParteFourModel
import org.koin.core.KoinComponent

class NuevaPropiedadParteFourVM : ViewModel(), KoinComponent {
  val nuevaPropiedadParteFourModel: MutableLiveData<NuevaPropiedadParteFourModel> =
      MutableLiveData(NuevaPropiedadParteFourModel())

  var navArguments: Bundle? = null
}
