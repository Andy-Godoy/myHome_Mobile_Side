package com.example.myhome.modules.modificarpropiedadpartethree.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.modificarpropiedadpartethree.`data`.model.ModificarPropiedadParteThreeModel
import org.koin.core.KoinComponent

class ModificarPropiedadParteThreeVM : ViewModel(), KoinComponent {
  val modificarPropiedadParteThreeModel: MutableLiveData<ModificarPropiedadParteThreeModel> =
      MutableLiveData(ModificarPropiedadParteThreeModel())

  var navArguments: Bundle? = null
}
