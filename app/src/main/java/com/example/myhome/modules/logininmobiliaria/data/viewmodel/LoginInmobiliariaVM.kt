package com.example.myhome.modules.logininmobiliaria.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.logininmobiliaria.`data`.model.LoginInmobiliariaModel
import org.koin.core.KoinComponent

class LoginInmobiliariaVM : ViewModel(), KoinComponent {
  val loginInmobiliariaModel: MutableLiveData<LoginInmobiliariaModel> =
      MutableLiveData(LoginInmobiliariaModel())

  var navArguments: Bundle? = null
}
