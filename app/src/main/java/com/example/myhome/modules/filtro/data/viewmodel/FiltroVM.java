package com.example.myhome.modules.filtro.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.filtro.`data`.model.FiltroModel
import com.example.myhome.modules.filtro.`data`.model.FiltroRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class FiltroVM : ViewModel(), KoinComponent {
  val filtroModel: MutableLiveData<FiltroModel> = MutableLiveData(FiltroModel())

  var navArguments: Bundle? = null

  val filtroList: MutableLiveData<MutableList<FiltroRowModel>> = MutableLiveData(mutableListOf())
}
