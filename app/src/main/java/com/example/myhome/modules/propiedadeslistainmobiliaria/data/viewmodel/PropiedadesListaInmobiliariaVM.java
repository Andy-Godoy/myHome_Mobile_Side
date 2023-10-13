package com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.model.PropiedadesListaInmobiliariaModel
import org.koin.core.KoinComponent

class PropiedadesListaInmobiliariaVM : ViewModel(), KoinComponent {
  val propiedadesListaInmobiliariaModel: MutableLiveData<PropiedadesListaInmobiliariaModel> =
      MutableLiveData(PropiedadesListaInmobiliariaModel())

  var navArguments: Bundle? = null
}
