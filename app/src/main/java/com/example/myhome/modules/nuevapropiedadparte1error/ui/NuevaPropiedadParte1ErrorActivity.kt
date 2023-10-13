package com.example.myhome.modules.nuevapropiedadparte1error.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityNuevaPropiedadParte1ErrorBinding
import com.example.myhome.modules.nuevapropiedadparte1error.`data`.model.ListnmeroRowModel
import com.example.myhome.modules.nuevapropiedadparte1error.`data`.viewmodel.NuevaPropiedadParte1ErrorVM
import com.example.myhome.modules.nuevapropiedadpartetwo.ui.NuevaPropiedadParteTwoActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class NuevaPropiedadParte1ErrorActivity :
    BaseActivity<ActivityNuevaPropiedadParte1ErrorBinding>(R.layout.activity_nueva_propiedad_parte_1_error)
    {
  private val viewModel: NuevaPropiedadParte1ErrorVM by viewModels<NuevaPropiedadParte1ErrorVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listnmeroAdapter = ListnmeroAdapter(viewModel.listnmeroList.value?:mutableListOf())
    binding.recyclerListnmero.adapter = listnmeroAdapter
    listnmeroAdapter.setOnItemClickListener(
    object : ListnmeroAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListnmeroRowModel) {
        onClickRecyclerListnmero(view, position, item)
      }
    }
    )
    viewModel.listnmeroList.observe(this) {
      listnmeroAdapter.updateData(it)
    }
    binding.nuevaPropiedadParte1ErrorVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = NuevaPropiedadParteTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListnmero(
    view: View,
    position: Int,
    item: ListnmeroRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "NUEVA_PROPIEDAD_PARTE1ERROR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NuevaPropiedadParte1ErrorActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
