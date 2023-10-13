package com.example.myhome.modules.nuevapropiedadpartetwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityNuevaPropiedadParteTwoBinding
import com.example.myhome.modules.nuevapropiedadparteone.ui.NuevaPropiedadParteOneActivity
import com.example.myhome.modules.nuevapropiedadpartethree.ui.NuevaPropiedadParteThreeActivity
import com.example.myhome.modules.nuevapropiedadpartetwo.`data`.model.GridtipopropiedadRowModel
import com.example.myhome.modules.nuevapropiedadpartetwo.`data`.viewmodel.NuevaPropiedadParteTwoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class NuevaPropiedadParteTwoActivity :
    BaseActivity<ActivityNuevaPropiedadParteTwoBinding>(R.layout.activity_nueva_propiedad_parte_two)
    {
  private val viewModel: NuevaPropiedadParteTwoVM by viewModels<NuevaPropiedadParteTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val gridtipopropiedadAdapter =
    GridtipopropiedadAdapter(viewModel.gridtipopropiedadList.value?:mutableListOf())
    binding.recyclerGridtipopropiedad.adapter = gridtipopropiedadAdapter
    gridtipopropiedadAdapter.setOnItemClickListener(
    object : GridtipopropiedadAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GridtipopropiedadRowModel) {
        onClickRecyclerGridtipopropiedad(view, position, item)
      }
    }
    )
    viewModel.gridtipopropiedadList.observe(this) {
      gridtipopropiedadAdapter.updateData(it)
    }
    binding.nuevaPropiedadParteTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = NuevaPropiedadParteThreeActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      val destIntent = NuevaPropiedadParteOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerGridtipopropiedad(
    view: View,
    position: Int,
    item: GridtipopropiedadRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "NUEVA_PROPIEDAD_PARTE_TWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NuevaPropiedadParteTwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
