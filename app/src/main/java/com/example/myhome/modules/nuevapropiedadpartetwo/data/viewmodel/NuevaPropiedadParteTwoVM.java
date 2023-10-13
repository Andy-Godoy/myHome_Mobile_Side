package com.example.myhome.modules.nuevapropiedadpartetwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model.GridtipopropiedadRowModel
import com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model.NuevaPropiedadParteTwoModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class NuevaPropiedadParteTwoVM : ViewModel(), KoinComponent {
  val nuevaPropiedadParteTwoModel: MutableLiveData<NuevaPropiedadParteTwoModel> =
      MutableLiveData(NuevaPropiedadParteTwoModel())

  var navArguments: Bundle? = null

  val gridtipopropiedadList: MutableLiveData<MutableList<GridtipopropiedadRowModel>> =
      MutableLiveData(mutableListOf())
}
