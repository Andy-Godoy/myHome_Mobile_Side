package com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.PropiedadesListaInmobiliariaOneModel
import org.koin.core.KoinComponent

class PropiedadesListaInmobiliariaOneVM : ViewModel(), KoinComponent {
  val propiedadesListaInmobiliariaOneModel: MutableLiveData<PropiedadesListaInmobiliariaOneModel> =
      MutableLiveData(PropiedadesListaInmobiliariaOneModel())

  var navArguments: Bundle? = null
}
