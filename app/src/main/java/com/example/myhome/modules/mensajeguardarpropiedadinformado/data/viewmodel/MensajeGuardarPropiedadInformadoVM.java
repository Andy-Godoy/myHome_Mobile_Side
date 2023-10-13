package com.example.myhome.modules.mensajeguardarpropiedadinformado.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.mensajeguardarpropiedadinformado.`data`.model.MensajeGuardarPropiedadInformadoModel
import org.koin.core.KoinComponent

class MensajeGuardarPropiedadInformadoVM : ViewModel(), KoinComponent {
  val mensajeGuardarPropiedadInformadoModel: MutableLiveData<MensajeGuardarPropiedadInformadoModel>
      = MutableLiveData(MensajeGuardarPropiedadInformadoModel())

  var navArguments: Bundle? = null
}
