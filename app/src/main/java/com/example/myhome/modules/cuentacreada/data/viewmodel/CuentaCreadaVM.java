package com.example.myhome.modules.cuentacreada.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.cuentacreada.`data`.model.CuentaCreadaModel
import org.koin.core.KoinComponent

class CuentaCreadaVM : ViewModel(), KoinComponent {
  val cuentaCreadaModel: MutableLiveData<CuentaCreadaModel> = MutableLiveData(CuentaCreadaModel())

  var navArguments: Bundle? = null
}
