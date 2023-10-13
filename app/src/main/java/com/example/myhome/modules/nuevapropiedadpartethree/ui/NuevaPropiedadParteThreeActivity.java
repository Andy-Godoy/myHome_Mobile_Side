package com.example.myhome.modules.nuevapropiedadpartethree.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityNuevaPropiedadParteThreeBinding
import com.example.myhome.modules.nuevapropiedadpartefour.ui.NuevaPropiedadParteFourActivity
import com.example.myhome.modules.nuevapropiedadpartethree.`data`.model.GridcubiertosRowModel
import com.example.myhome.modules.nuevapropiedadpartethree.`data`.viewmodel.NuevaPropiedadParteThreeVM
import com.example.myhome.modules.nuevapropiedadpartetwo.ui.NuevaPropiedadParteTwoActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class NuevaPropiedadParteThreeActivity :
    BaseActivity<ActivityNuevaPropiedadParteThreeBinding>(R.layout.activity_nueva_propiedad_parte_three)
    {
  private val viewModel: NuevaPropiedadParteThreeVM by viewModels<NuevaPropiedadParteThreeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val gridcubiertosAdapter =
    GridcubiertosAdapter(viewModel.gridcubiertosList.value?:mutableListOf())
    binding.recyclerGridcubiertos.adapter = gridcubiertosAdapter
    gridcubiertosAdapter.setOnItemClickListener(
    object : GridcubiertosAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GridcubiertosRowModel) {
        onClickRecyclerGridcubiertos(view, position, item)
      }
    }
    )
    viewModel.gridcubiertosList.observe(this) {
      gridcubiertosAdapter.updateData(it)
    }
    binding.nuevaPropiedadParteThreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = NuevaPropiedadParteFourActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      val destIntent = NuevaPropiedadParteTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerGridcubiertos(
    view: View,
    position: Int,
    item: GridcubiertosRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "NUEVA_PROPIEDAD_PARTE_THREE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NuevaPropiedadParteThreeActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
