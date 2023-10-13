package com.example.myhome.modules.mailcontraseaenviado.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.mailcontraseaenviado.`data`.model.MailContraseAEnviadoModel
import org.koin.core.KoinComponent

class MailContraseAEnviadoVM : ViewModel(), KoinComponent {
  val mailContraseAEnviadoModel: MutableLiveData<MailContraseAEnviadoModel> =
      MutableLiveData(MailContraseAEnviadoModel())

  var navArguments: Bundle? = null
}
