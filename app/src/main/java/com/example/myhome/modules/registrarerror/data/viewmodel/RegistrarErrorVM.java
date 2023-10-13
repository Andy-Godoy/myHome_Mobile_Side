package com.example.myhome.modules.registrarerror.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.registrarerror.`data`.model.RegistrarErrorModel
import org.koin.core.KoinComponent

class RegistrarErrorVM : ViewModel(), KoinComponent {
  val registrarErrorModel: MutableLiveData<RegistrarErrorModel> =
      MutableLiveData(RegistrarErrorModel())

  var navArguments: Bundle? = null
}
