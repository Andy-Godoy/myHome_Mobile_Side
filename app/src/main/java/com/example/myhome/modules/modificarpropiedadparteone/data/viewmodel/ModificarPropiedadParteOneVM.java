package com.example.myhome.modules.modificarpropiedadparteone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.modificarpropiedadparteone.`data`.model.ModificarPropiedadParteOneModel
import org.koin.core.KoinComponent

class ModificarPropiedadParteOneVM : ViewModel(), KoinComponent {
  val modificarPropiedadParteOneModel: MutableLiveData<ModificarPropiedadParteOneModel> =
      MutableLiveData(ModificarPropiedadParteOneModel())

  var navArguments: Bundle? = null
}
