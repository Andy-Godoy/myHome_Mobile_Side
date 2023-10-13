package com.example.myhome.modules.reservar.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.reservar.`data`.model.ReservarModel
import org.koin.core.KoinComponent

class ReservarVM : ViewModel(), KoinComponent {
  val reservarModel: MutableLiveData<ReservarModel> = MutableLiveData(ReservarModel())

  var navArguments: Bundle? = null
}
