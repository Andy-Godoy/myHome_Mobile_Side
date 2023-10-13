package com.example.myhome.modules.inmobiliariacompartirpropiedad.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.inmobiliariacompartirpropiedad.`data`.model.InmobiliariaCompartirPropiedadModel
import org.koin.core.KoinComponent

class InmobiliariaCompartirPropiedadVM : ViewModel(), KoinComponent {
  val inmobiliariaCompartirPropiedadModel: MutableLiveData<InmobiliariaCompartirPropiedadModel> =
      MutableLiveData(InmobiliariaCompartirPropiedadModel())

  var navArguments: Bundle? = null
}
