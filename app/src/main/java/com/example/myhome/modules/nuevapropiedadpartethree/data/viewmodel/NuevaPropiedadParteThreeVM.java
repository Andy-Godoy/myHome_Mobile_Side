package com.example.myhome.modules.nuevapropiedadpartethree.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.nuevapropiedadpartethree.`data`.model.GridcubiertosRowModel
import com.example.myhome.modules.nuevapropiedadpartethree.`data`.model.NuevaPropiedadParteThreeModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class NuevaPropiedadParteThreeVM : ViewModel(), KoinComponent {
  val nuevaPropiedadParteThreeModel: MutableLiveData<NuevaPropiedadParteThreeModel> =
      MutableLiveData(NuevaPropiedadParteThreeModel())

  var navArguments: Bundle? = null

  val gridcubiertosList: MutableLiveData<MutableList<GridcubiertosRowModel>> =
      MutableLiveData(mutableListOf())
}
