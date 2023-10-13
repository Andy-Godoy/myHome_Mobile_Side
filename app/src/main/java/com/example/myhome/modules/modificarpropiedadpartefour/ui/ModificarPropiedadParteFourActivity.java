package com.example.myhome.modules.modificarpropiedadpartefour.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityModificarPropiedadParteFourBinding
import com.example.myhome.modules.modificarpropiedadpartefour.`data`.model.ListticketRowModel
import com.example.myhome.modules.modificarpropiedadpartefour.`data`.viewmodel.ModificarPropiedadParteFourVM
import com.example.myhome.modules.modificarpropiedadpartethree.ui.ModificarPropiedadParteThreeActivity
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ModificarPropiedadParteFourActivity :
    BaseActivity<ActivityModificarPropiedadParteFourBinding>(R.layout.activity_modificar_propiedad_parte_four)
    {
  private val viewModel: ModificarPropiedadParteFourVM by
      viewModels<ModificarPropiedadParteFourVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listticketAdapter = ListticketAdapter(viewModel.listticketList.value?:mutableListOf())
    binding.recyclerListticket.adapter = listticketAdapter
    listticketAdapter.setOnItemClickListener(
    object : ListticketAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListticketRowModel) {
        onClickRecyclerListticket(view, position, item)
      }
    }
    )
    viewModel.listticketList.observe(this) {
      listticketAdapter.updateData(it)
    }
    binding.modificarPropiedadParteFourVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = ModificarPropiedadParteThreeActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearGuardar.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListticket(
    view: View,
    position: Int,
    item: ListticketRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "MODIFICAR_PROPIEDAD_PARTE_FOUR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ModificarPropiedadParteFourActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
