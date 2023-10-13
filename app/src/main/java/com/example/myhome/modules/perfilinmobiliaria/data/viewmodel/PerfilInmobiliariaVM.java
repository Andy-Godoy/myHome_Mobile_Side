package com.example.myhome.modules.perfilinmobiliaria.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.perfilinmobiliaria.`data`.model.PerfilInmobiliariaModel
import org.koin.core.KoinComponent

class PerfilInmobiliariaVM : ViewModel(), KoinComponent {
  val perfilInmobiliariaModel: MutableLiveData<PerfilInmobiliariaModel> =
      MutableLiveData(PerfilInmobiliariaModel())

  var navArguments: Bundle? = null
}
