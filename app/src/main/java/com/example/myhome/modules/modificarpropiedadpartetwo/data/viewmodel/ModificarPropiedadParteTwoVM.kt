package com.example.myhome.modules.modificarpropiedadpartetwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.modificarpropiedadpartetwo.`data`.model.ModificarPropiedadParteTwoModel
import org.koin.core.KoinComponent

class ModificarPropiedadParteTwoVM : ViewModel(), KoinComponent {
  val modificarPropiedadParteTwoModel: MutableLiveData<ModificarPropiedadParteTwoModel> =
      MutableLiveData(ModificarPropiedadParteTwoModel())

  var navArguments: Bundle? = null
}
