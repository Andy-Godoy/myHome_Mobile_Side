package com.example.myhome.modules.filtroone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.filtroone.`data`.model.FiltroOneModel
import com.example.myhome.modules.filtroone.`data`.model.FiltroOneRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class FiltroOneVM : ViewModel(), KoinComponent {
  val filtroOneModel: MutableLiveData<FiltroOneModel> = MutableLiveData(FiltroOneModel())

  var navArguments: Bundle? = null

  val filtroOneList: MutableLiveData<MutableList<FiltroOneRowModel>> =
      MutableLiveData(mutableListOf())
}
