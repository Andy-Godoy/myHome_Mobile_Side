package com.example.myhome.modules.perfilinmobiliariaone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.perfilinmobiliariaone.`data`.model.PerfilInmobiliariaOneModel
import org.koin.core.KoinComponent

class PerfilInmobiliariaOneVM : ViewModel(), KoinComponent {
  val perfilInmobiliariaOneModel: MutableLiveData<PerfilInmobiliariaOneModel> =
      MutableLiveData(PerfilInmobiliariaOneModel())

  var navArguments: Bundle? = null
}
