package com.example.myhome.modules.nuevapropiedadparte1error.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.nuevapropiedadparte1error.`data`.model.ListnmeroRowModel
import com.example.myhome.modules.nuevapropiedadparte1error.`data`.model.NuevaPropiedadParte1ErrorModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class NuevaPropiedadParte1ErrorVM : ViewModel(), KoinComponent {
  val nuevaPropiedadParte1ErrorModel: MutableLiveData<NuevaPropiedadParte1ErrorModel> =
      MutableLiveData(NuevaPropiedadParte1ErrorModel())

  var navArguments: Bundle? = null

  val listnmeroList: MutableLiveData<MutableList<ListnmeroRowModel>> =
      MutableLiveData(mutableListOf())
}
