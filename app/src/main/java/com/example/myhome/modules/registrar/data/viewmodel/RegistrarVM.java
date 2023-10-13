package com.example.myhome.modules.registrar.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.registrar.`data`.model.RegistrarModel
import org.koin.core.KoinComponent

class RegistrarVM : ViewModel(), KoinComponent {
  val registrarModel: MutableLiveData<RegistrarModel> = MutableLiveData(RegistrarModel())

  var navArguments: Bundle? = null
}
