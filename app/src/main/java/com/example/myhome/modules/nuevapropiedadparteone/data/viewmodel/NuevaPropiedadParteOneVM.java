package com.example.myhome.modules.nuevapropiedadparteone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.nuevapropiedadparteone.`data`.model.NuevaPropiedadParteOneModel
import org.koin.core.KoinComponent

class NuevaPropiedadParteOneVM : ViewModel(), KoinComponent {
  val nuevaPropiedadParteOneModel: MutableLiveData<NuevaPropiedadParteOneModel> =
      MutableLiveData(NuevaPropiedadParteOneModel())

  var navArguments: Bundle? = null
}
